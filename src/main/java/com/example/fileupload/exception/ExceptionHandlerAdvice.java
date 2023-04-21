package com.example.fileupload.exception;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice  implements ErrorController {

    @ExceptionHandler(ImageUploadException.class)
    public ModelAndView handleImageUploadException(ImageUploadException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", ex);
        return mav;
    }

}
