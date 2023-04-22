package com.example.fileupload.dao;

import com.example.fileupload.domain.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MultiFileUploadDAOImpl extends JdbcDaoSupport implements MultiFileUploadDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private LobHandler lobHandler;

    @Autowired
    public MultiFileUploadDAOImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<FileVO> getAllFileList() {
        final String sql = "select no, title, wdate from file_upload order by no desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FileVO.class));
    }

    @Override
    public int addFileWritePicture(FileVO vo) {
        final String sql = "insert into file_upload(no, title, content, wdate, picture) " +
                "values(file_upload_no_seq.nextval, ?, ?, sysdate, ?)";
        return jdbcTemplate.execute(sql, (PreparedStatementCallback<Integer>) ps -> {
            ps.setString(1, vo.getTitle());
            ps.setString(2, vo.getContent());
            try {
                lobHandler.getLobCreator().setBlobAsBytes(ps, 3, vo.getPicture().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return ps.executeUpdate();
        });
    }

    @Override
    public FileVO getFileUpload(Integer no) {
        final String sql = "select no, title, content, wdate from file_upload where no=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(FileVO.class), no);
    }

    @Override
    public InputStream getPicture(Integer no) {
        final String sql = "select picture from file_upload where no=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{no}, (rs, rowNum) -> lobHandler.getBlobAsBinaryStream(rs, "picture"));
    }

    @Override
    public int modifyFileUpload(FileVO vo) {
        final String sql = "update file_upload set title=?, content=? where no=?";
        return jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getNo());
    }

    @Override
    public int modifyFileWithPicture(FileVO vo) {
        final String sql = "update file_upload set title=?, content=?, picture=? where no=?";
        return jdbcTemplate.execute(sql, (PreparedStatementCallback<Integer>) ps -> {
            ps.setString(1, vo.getTitle());
            ps.setString(2, vo.getContent());
            try {
                lobHandler.getLobCreator().setBlobAsBytes(ps, 3, vo.getPicture().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ps.setInt(4, vo.getNo());
            return ps.executeUpdate();
        });
    }

    @Override
    public int removeFile(Integer no) {
        final String sql = "delete from file_upload where no=?";
        return jdbcTemplate.update(sql, no);
    }
}
