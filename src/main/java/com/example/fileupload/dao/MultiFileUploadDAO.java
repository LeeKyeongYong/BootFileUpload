package com.example.fileupload.dao;

import com.example.fileupload.domain.FileVO;

import java.io.InputStream;
import java.util.List;

public interface MultiFileUploadDAO {
    public List<FileVO> getAllFileList();
    public int addFileWritePicture(FileVO vo);
    public FileVO getFileUpload(Integer no);
    public InputStream getPicture(Integer no);
    public int modifyFileUpload(FileVO vo);
    public int modifyFileWithPicture(final FileVO vo);
    public int removeFile(Integer no);
}
