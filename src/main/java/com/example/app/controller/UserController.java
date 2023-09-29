package com.example.app.controller;

//import com.example.app.aspect.annotation.LoggingPointcut;
import com.example.app.domain.dto.UserDto;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage(){
        return "user/login";
    }

    @GetMapping("/join")
    public String showJoinPage(){
        return "user/join";
    }

    @PostMapping("/login")
    public RedirectView login(String userId, String userPassword, HttpServletRequest req){
        Long userNumber = userService.findUserNumber(userId, userPassword);
        req.getSession().setAttribute("userNumber", userNumber);

        return new RedirectView("/board/list");
    }

//    @LoggingPointcut
    @PostMapping("/join")
    public RedirectView join(UserDto userDto){
        userService.register(userDto);

        return new RedirectView("/user/login");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        req.getSession().invalidate();
        return "user/login";
    }
}











