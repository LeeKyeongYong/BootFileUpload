package com.example.fileupload.dao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.sql.DataSource;
import com.example.fileupload.domain.FileUploadDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class FileUploadDAOImplTest {
    @Autowired
    private DataSource dataSource;

    private FileUploadDAO fileUploadDAO;

    @Before
    public void setUp() {
        fileUploadDAO = new FileUploadDAOImpl(dataSource);
    }

    @Test
    public void testGetAllFileList() {
        List<FileUploadDTO> fileList = fileUploadDAO.getAllFileList();
        assertNotNull(fileList);
        assertTrue(fileList.size() > 0);
    }

    @Test
    public void testGetFileUpload() {
        Integer no = 1;
        FileUploadDTO fileUploadDTO = fileUploadDAO.getFileUpload(no);
        assertNotNull(fileUploadDTO);
        assertEquals(no, fileUploadDTO.getNo());
    }

    @Test
    public void testGetSequenceNo() {
        int sequenceNo = fileUploadDAO.getSequenceNo();
        assertTrue(sequenceNo > 0);
    }

    @Test
    public void testAddFileUpload() {
        int sequenceNo = fileUploadDAO.getSequenceNo();
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setNo(sequenceNo);
        fileUploadDTO.setTitle("Test Title");
        fileUploadDTO.setContent("Test Content");
        int result = fileUploadDAO.addFileUpload(fileUploadDTO);
        assertTrue(result > 0);
    }

    @Test
    public void testRemoveFileUpload() {
        Integer no = 1;
        int result = fileUploadDAO.removeFileUpload(no);
        assertTrue(result > 0);
    }

    @Test
    public void testModifyFileUpload() {
        Integer no = 1;
        FileUploadDTO fileUploadDTO = fileUploadDAO.getFileUpload(no);
        String updatedTitle = "Updated Title";
        fileUploadDTO.setTitle(updatedTitle);
        int result = fileUploadDAO.modifyFileUpload(fileUploadDTO);
        assertTrue(result > 0);
        fileUploadDTO = fileUploadDAO.getFileUpload(no);
        assertEquals(updatedTitle, fileUploadDTO.getTitle());
    }

}