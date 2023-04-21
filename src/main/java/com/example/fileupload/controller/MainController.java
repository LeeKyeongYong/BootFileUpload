package com.example.fileupload.controller;

import com.example.fileupload.dao.FileUploadDAO;
import com.example.fileupload.domain.FileUploadDTO;
import com.example.fileupload.util.MyUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    @Autowired
    private FileUploadDAO dao;


    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "filupload/fileUpload.do", method = RequestMethod.GET)
    public String main_one(Model model) {
        model.addAttribute("fieleList", dao.getAllFileList());
        return "main_one";
    }

    @RequestMapping(value="filupload/fileUploadWrite.do",method = RequestMethod.GET)
    public String fileUploadView(Model model){
        model.addAttribute("file",new FileUploadDTO());
        return "write";
    }
    @RequestMapping(value="filupload/fileUploadWrite.do",method = RequestMethod.POST)
    public String fileUploadSave(@RequestParam(value="title")String title,
                                 @RequestParam(value="content")String content,
                                 @RequestParam(value="picture")MultipartFile picture,
                                 HttpSession session,
                                 Model model){
        int no=dao.getSequenceNo();

        MyUtil.validateImage(picture);
        MyUtil.saveImage(MyUtil.getRealFilename(no,session),picture);

        FileUploadDTO dto=new FileUploadDTO(no,title,content,null);
        dao.addFileUpload(dto);
        return "redirect:/index";
    }

    //filupload/fileUploadView.do
    //@RequestMapping(value="filupload/fileUploadView.do",method=RequestMethod.GET)

    //filupload/fileUploadWrite.do

    //filupload/fileUploadUpload.do

    //filupload/fileUploadRemove.do

}
