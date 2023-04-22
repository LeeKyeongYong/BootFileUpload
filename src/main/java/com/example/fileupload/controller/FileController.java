package com.example.fileupload.controller;

import com.example.fileupload.dao.MultiFileUploadDAO;
import com.example.fileupload.domain.FileUploadDTO;
import com.example.fileupload.domain.FileVO;
import com.example.fileupload.util.MyUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

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
    @RequestMapping(value = "filupload/m_fileUploadImage.do", method = RequestMethod.GET)
    public void m_fileUploadImage(@RequestParam(value = "no") Integer no,
                                  HttpServletResponse response) {
        response.setContentType("image/jpeg");
        InputStream picture = null;
        OutputStream os = null;
        BufferedInputStream bis = null;

        try {
            picture = dao.getPicture(no);

            if(os == null) {
                os = response.getOutputStream();
            }
            bis = new BufferedInputStream(picture);
            int data;
            while((data = bis.read())!=-1) {
                os.write(data);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } finally {
            try {
                if(picture != null) {
                    os.close();
                    bis.close();
                }
            } catch (IOException e) {}
        }

    }

    //수정 폼
    @RequestMapping(value="filupload/m_fileUploadEdit.do",method=RequestMethod.GET)
    public String edit(@RequestParam(value = "no") Integer no, Model model) {
        model.addAttribute("file",dao.getFileUpload(no));
        return "m_edit";
    }

    //수정하기
    @RequestMapping(value="filupload/m_fileUploadEdit.do",method=RequestMethod.POST)
    public String modify(@RequestParam(value="title") String title,
                         @RequestParam(value="no")Integer no,
                         @RequestParam(value="content")String content,
                         @RequestParam(value = "picture", required = false) MultipartFile picture) {
        if(picture.getOriginalFilename().length()==0){
            dao.modifyFileUpload(new FileVO(no,title,content,null,null));
        } else {
                if(!picture.getContentType().equals("image/jpeg")){
                    throw new RuntimeException("image/jpeg 형식의 이미지만 업로드 가능합니다.");
                }
                dao.modifyFileWithPicture(new FileVO(no,title,content,null,picture));
        }
        return"redirect:/filupload/m_fileUploadView.do?no="+no.intValue();
    }



    //삭제
    @RequestMapping(value = "filupload/m_fileUploadRemove.do", method = RequestMethod.GET)
    public String remove(Integer no) {
        dao.removeFile(no);
        return "redirect:/index";
    }
}
