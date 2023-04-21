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

    @RequestMapping(value="filupload/fileUploadView.do",method=RequestMethod.GET)
    public String view(@RequestParam(value="no")Integer no,Model model){
        FileUploadDTO dto=dao.getFileUpload(no);
        dto.setContent(dto.getContent().replace("\r\n","<br/>"));
        model.addAttribute("file",dto);
        model.addAttribute("imageUrl",MyUtil.getImageUrl(no.intValue()));
        return "view";
    }

    @RequestMapping(value="filupload/fileUploadEdit.do",method=RequestMethod.GET)
    public String edit(@RequestParam(value="no")Integer no,Model model,HttpSession session){
        FileUploadDTO dto=dao.getFileUpload(no);
        model.addAttribute("file",dto);
        model.addAttribute("imageUrl",MyUtil.getImageUrl(no.intValue()));
        return "edit";
    }

    @RequestMapping(value="filupload/fileUploadEdit.do",method=RequestMethod.POST)
    public String modify(@RequestParam(value="title") String title,
                         @RequestParam(value="no")Integer no,
                         @RequestParam(value="content")String content,
                         @RequestParam(value="picture")MultipartFile picture,
                         HttpSession session,
                         Model model) {
        String fileName = MyUtil.getRealFilename(no.intValue(), session);
        if (picture.getOriginalFilename().length() != 0) {
            MyUtil.validateImage(picture);
            MyUtil.removeImage(fileName);
            MyUtil.saveImage(fileName, picture);
        }
        FileUploadDTO dto = new FileUploadDTO(no, title, content, null);
        dao.modifyFileUpload(dto);
        return"redirect:/filupload/fileUploadView.do?no="+no.intValue();
    }

    @RequestMapping(value="filupload/fileUploadRemove.do",method=RequestMethod.GET)
    public String remove(@RequestParam(value="no")Integer no,HttpSession session){
        String fileName=session.getServletContext().getRealPath("/resources/images")+"/image"+no+".jpg";
        MyUtil.removeImage(fileName);
        dao.removeFileUpload(no);
        return"redirect:/index";
    }

}
