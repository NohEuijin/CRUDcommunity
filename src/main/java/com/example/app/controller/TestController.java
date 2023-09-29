package com.example.app.controller;

import com.example.app.domain.dto.SummerDto;
import com.example.app.service.SummerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/summernote/*")
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private final SummerService summerService;

    @GetMapping("/test")
    public String testPage(){
        return "summernote/test";
    }

    @PostMapping("/write")
    public String write(SummerDto summerDto){
//        log.info(content);
        log.info("=================================================={}", summerDto.getSummerContent());
        summerService.register(summerDto);

        return "summernote/result";
    }

    @GetMapping("/read")
    public String read(Long summerNumber, Model model){
        model.addAttribute("summerDto", summerService.find(summerNumber));

        return "summernote/result";
    }
}














