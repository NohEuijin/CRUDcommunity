package com.example.app.controller;

import com.example.app.domain.vo.BoardVo;
import com.example.app.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BoardController.class) //특정 컨트롤러와 관련된 빈만 컨테이너에 등록
@ExtendWith(SpringExtension.class) // JUnit을 사용할 때 스프링 컨테이너 일부 기능을 사용하기 위한 어노테이션
@Slf4j
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    void showListPage() {
    }

    @Test
    void showDetailPage() throws Exception{

        doReturn(new BoardVo()).when(boardService).find(any(Long.class));

        String result = mockMvc.perform(
                get("/board/detail")
                        .param("boardNumber", "43")
        ).andReturn()
                .getModelAndView()
                .getModelMap() + "";

        log.info(result);
    }

    @Test
    void showWritePage() {
    }

    @Test
    void boardWrite() throws Exception {
        log.info(
                mockMvc.perform(post("/board/write")
                .param("boardTitle", "test")
                .param("boardContent", "test Content")
                .sessionAttr("userNumber", 1L))
                .andReturn()
                .getFlashMap() + ""
        );
    }

    @Test
    void remove() {
    }

    @Test
    void modify() {
    }
}