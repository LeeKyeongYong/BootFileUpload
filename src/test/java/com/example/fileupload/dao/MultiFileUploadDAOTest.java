package com.example.fileupload.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.fileupload.domain.FileVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.fileupload.domain.*;

@RunWith(MockitoJUnitRunner.class)
public class MultiFileUploadDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MultiFileUploadDAOImpl multiFileUploadDAO;

    private List<FileVO> fileList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Create some sample data for the test
        fileList = new ArrayList<>();
        fileList.add(new FileVO(6, "4424","4424", new Date()));
        fileList.add(new FileVO(5, "7","7", new Date()));
        fileList.add(new FileVO(3, "12347277","12347277", new Date()));
    }

    @Test
    public void testGetAllFileList() {
        // Given
        final String sql = "select no, title,content,wdate from file_upload order by no desc";
        when(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FileVO.class))).thenReturn(fileList);
        // When
        List<FileVO> result = multiFileUploadDAO.getAllFileList();
        // Then
        assertEquals(fileList, result);
    }
    
}
