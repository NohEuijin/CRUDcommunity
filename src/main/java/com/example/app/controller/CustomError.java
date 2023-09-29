package com.example.app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomError implements ErrorController {
    @GetMapping("/error")
    public String error(HttpServletRequest req){
//        HTTP상태 코드를 req에서 받을 수 있다.
        Object attribute = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(attribute != null){
            int statusCode = Integer.valueOf(attribute.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "error/404"; // 404 : 요청 페이지 찾을 수 없음(컨트롤러에 매핑된 메소드가 없다는 의미)
            }
        }

        return "error/500"; //500 : 서버 오류
    }
}











