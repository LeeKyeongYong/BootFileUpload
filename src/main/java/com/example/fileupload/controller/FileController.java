package com.example.fileupload.controller;

import com.example.fileupload.dao.MultiFileUploadDAO;
import com.example.fileupload.domain.FileUploadDTO;
import com.example.fileupload.domain.FileVO;
import com.example.fileupload.util.MyUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class FileController {

    @Autowired
    private MultiFileUploadDAO dao;

    //리스트
    @RequestMapping(value = "filupload/fileUpload2.do", method = RequestMethod.GET)
    public String main_two(Model model) {
       model.addAttribute("fieleList", dao.getAllFileList());
        return "main_two";
    }

    //글쓰기폼
    @RequestMapping(value="filupload/m_fileUploadWrite.do",method = RequestMethod.GET)
    public String m_fileUploadWriteForm(Model model){
        model.addAttribute("title", "한글제목");
        return "m_write";
    }

    //글쓰기
    @RequestMapping(value="filupload/m_fileUploadWrite.do",method = RequestMethod.POST)
    public String m_fileUploadWrite(@RequestParam(value="title") String title,
                                    @RequestParam(value="content")String content,
                                    @RequestParam(value="picture",required = false)MultipartFile picture){
        if(!picture.getContentType().equals("image/jpeg")){
            throw new RuntimeException("image/jpeg 형식의 이미지만 업로드 가능합니다.");
        }
        dao.addFileWritePicture(new FileVO(null,title,content,null,picture));
        return "redirect:/index";
    }

    //뷰폼
    @RequestMapping(value="filupload/m_fileUploadView.do",method=RequestMethod.GET)
    public String m_fileUploadView(@RequestParam(value="no")Integer no, Model model){
        FileVO dto=dao.getFileUpload(no);
        dto.setContent(dto.getContent().replace("\r\n","<br/>"));
        model.addAttribute("file",dto);
        return "m_view";
    }

    //이미지
    @RequestMapping(value="filupload/m_fileUploadImage.do",method = RequestMethod.GET)
    public void m_fileUploadImage(@RequestParam(value = "no") Integer no,
                                  HttpServletResponse response) {
        response.setContentType("image/jpeg");
        InputStream picture = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            picture = dao.getPicture(no);

            os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = picture.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (picture != null) {
                    picture.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {}
        }
    }


    //수정 폼



    //수정하기




    //삭제
    @RequestMapping(value = "filupload/m_fileUploadRemove.do", method = RequestMethod.GET)
    public String remove(Integer no) {
        dao.removeFile(no);
        return "redirect:/index";
    }
}
