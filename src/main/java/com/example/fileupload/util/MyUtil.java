package com.example.fileupload.util;

import java.io.File;
import java.util.Date;

import jakarta.servlet.http.HttpSession;


import com.example.fileupload.exception.ImageUploadException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class MyUtil {

    public static String getRealFilename(int no, HttpSession session) {
        return session.getServletContext().getRealPath("/upload/images") + "/image" + no + ".jpg";
    }

    public static String getImageUrl(int no) {
        return "/upload/images/image" + no + ".jpg";
    }

    public static void validateImage(MultipartFile image) {
        String contentType = image.getContentType();
        if (contentType == null ||!contentType.startsWith("image/")) {
            throw new ImageUploadException("Your file : " + image.getContentType() + ". Only image accepted");
        }
    }

    public static void saveImage(String filename, MultipartFile image) {
        try {
            File file = new File(filename);
            if(file.exists()) {
                removeImage(filename);
            }
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (Exception e) {
            throw new ImageUploadException("Unable to save image");
        }
    }

    public static void removeImage(String filename) {
        try {
            File file = new File(filename);
            if(file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            throw new ImageUploadException("Unable to remove image");
        }
    }
}
