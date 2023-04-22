package com.example.fileupload.dao;

import com.example.fileupload.domain.FileVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileUploadDAOTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private LobHandler lobHandler;

    @InjectMocks
    private MultiFileUploadDAOImpl multiFileUploadDAO;

    private FileVO fileVO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        fileVO = new FileVO();
        fileVO.setNo(1);
        fileVO.setTitle("Test Title");
        fileVO.setContent("Test Content");

        // 가짜 MultipartFile 객체 생성
        MultipartFile file = Mockito.mock(MultipartFile.class);
        try {
            Mockito.when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileVO.setPicture(file);
    }


    @Test
    public void testModifyFileUpload() {
        // Given
        fileVO.setTitle("New Title");
        fileVO.setContent("New Content");

        // When
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(1);

        int result = multiFileUploadDAO.modifyFileUpload(fileVO);

        // Then
        assertEquals(1, result);
        Mockito.verify(jdbcTemplate).update(Mockito.anyString(), Mockito.eq("New Title"), Mockito.eq("New Content"), Mockito.eq(1));
    }

}
