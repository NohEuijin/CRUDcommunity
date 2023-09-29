package com.example.app.controller;

//import com.example.app.aspect.annotation.LoggingPointcut;
import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.PageVo;
import com.example.app.service.BoardService;
import com.example.app.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

//    @LoggingPointcut
    @GetMapping("/list")
    public String showListPage(Criteria criteria, Model model){
        model.addAttribute("boardList", boardService.findAll(criteria));
        model.addAttribute("pageInfo", new PageVo(boardService.getTotal(), criteria));
        return "board/boardList";
    }

    @GetMapping(value = {"/detail", "/modify"})
    public void showDetailPage(Long boardNumber, Model model){
        BoardVo boardVo = boardService.find(boardNumber);
        model.addAttribute("board", boardVo);

    }

    @GetMapping("/write")
    public String showWritePage(HttpServletRequest req){
        Long userNumber= (Long)req.getSession().getAttribute("userNumber");

        return userNumber == null ? "user/login" : "board/boardWrite";
    }

    @PostMapping("/write")
    public RedirectView boardWrite(BoardDto boardDto, HttpServletRequest req, RedirectAttributes redirectAttributes,
                                   @RequestParam("boardFile") List<MultipartFile> files){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");

        boardDto.setUserNumber(userNumber);
        boardService.registerAndFileProc(boardDto, files);

        Long boardNumber = boardDto.getBoardNumber();


//        쿼리스트링으로 데이터를 전송한다. -> 다시 요청을 보내는 메소드에서 데이터를 사용할 때
//        redirectAttributes.addAttribute("boardNumber", boardNumber);

//        플래쉬로 데이터를 전송 -> 화면에 데이터를 전송할 때 주로 사용
        redirectAttributes.addFlashAttribute("boardNumber", boardNumber);

        return new RedirectView("/board/list");
    }

    @GetMapping("/remove")
    public RedirectView remove(Long boardNumber){
        boardService.remove(boardNumber);
        return new RedirectView("/board/list");
    }

    @PostMapping("/modify")
    public RedirectView modify(BoardDto boardDto, RedirectAttributes redirectAttributes,
                               @RequestParam("boardFile") List<MultipartFile> files){

        log.info("===================================== {}", files.toString());
        boardService.modify(boardDto, files);

        redirectAttributes.addAttribute("boardNumber", boardDto.getBoardNumber());

        return new RedirectView("/board/detail");
    }


}


























