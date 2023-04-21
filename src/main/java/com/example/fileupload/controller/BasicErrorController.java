package com.example.fileupload.controller;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BasicErrorController implements ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 에러 페이지 정의
    private final String ERROR_ETC_PAGE_PATH = "error";

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {


        // 에러 코드를 획득한다.
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // 에러 코드에 대한 상태 정보
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        int statusCode = Integer.valueOf(status.toString());

        if (status != null) {
            // 로그로 상태값을 기록 및 출력
            logger.info("httpStatus : " + statusCode);
            logger.error("message"+status.toString() + "\n" + httpStatus.getReasonPhrase() + "\n" + new Date());
            // 에러 페이지에 표시할 정보
            model.addAttribute("message", "<center>"+status.toString() + "<br/>" + httpStatus.getReasonPhrase() +
                    "<br/>" + new Date()+"</center>");
        }

        // 에러 페이지로 보낸다.
        return ERROR_ETC_PAGE_PATH;
    }
}