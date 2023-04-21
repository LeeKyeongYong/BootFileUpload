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
public class ExceptionHandlerAdvice implements ErrorController {
    @ExceptionHandler(ImageUploadException.class)
    public ModelAndView handleImageUploadException(ImageUploadException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", ex);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleOtherException(Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", ex);
        return mav;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error"; // 404 페이지 이름
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error"; // 500 페이지 이름
            }
        }
        return "error"; // 기본 에러 페이지 이름
    }

}
