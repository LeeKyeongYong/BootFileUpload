package com.example.fileupload.dao;


import com.example.fileupload.domain.FileUploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class FileUploadDAOImpl extends NamedParameterJdbcDaoSupport implements FileUploadDAO {

    public FileUploadDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public List<FileUploadDTO> getAllFileList() {
        final String sql="select no,title,wdate from board_picture order by no desc";
        return this.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<FileUploadDTO>(FileUploadDTO.class));
    }

    @Override
    public FileUploadDTO getFileUpload(Integer no) {
        final String sql="select * from board_picture where no=?";
        return this.getJdbcTemplate().queryForObject(sql,new BeanPropertyRowMapper<FileUploadDTO>(FileUploadDTO.class),no);
    }

    @Override
    public int getSequenceNo() {
        final String sql="select board_picture_no_seq.nextval from dual";
        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public int addFileUpload(FileUploadDTO dto) {
        final String sql="insert into board_picture(no,title,content,wdate)" +
                "values(:no,:title,:content,sysdate)";
        return this.getNamedParameterJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(dto));
    }

    @Override
    public int removeFileUpload(Integer no) {
        final String sql="delete from board_picture where no=?";
        return this.getJdbcTemplate().update(sql,no);
    }

    @Override
    public int modifyFileUpload(FileUploadDTO dto) {
        final String sql="update board_picture set title=:title,content=:content where no=:no";
        return this.getNamedParameterJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(dto));
    }
}
