package com.example.fileupload.dao;

import com.example.fileupload.domain.FileUploadDTO;

import java.util.List;

public interface FileUploadDAO {
    public List<FileUploadDTO> getAllFileList();
    public FileUploadDTO getFileUpload(Integer no);
    public int getSequenceNo();
    public int addFileUpload(FileUploadDTO dto);
    public int removeFileUpload(Integer no);
    public int modifyFileUpload(FileUploadDTO dto);
}
