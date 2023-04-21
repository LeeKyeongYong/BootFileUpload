package com.example.fileupload.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ImageUploadException.class)
    public String handleImageUploadException(ImageUploadException ex, Model model) {
        // 예외 처리 로직을 여기에 작성합니다.
        model.addAttribute("error", ex.getMessage());
        return "error"; // 예외 처리 후 보여줄 뷰 이름을 반환합니다.
    }
}
