package com.example.app.service;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import com.example.app.mapper.BoardMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    BoardMapper boardMapper;

    @InjectMocks
    BoardService boardService;

    BoardDto boardDto;
    BoardVo boardVo;

    @BeforeEach
    void setUp() {
        boardDto = new BoardDto();
        boardDto.setBoardTitle("test");
        boardDto.setBoardContent("content");

        boardVo = new BoardVo();
        boardVo.setBoardTitle("test");
        boardVo.setBoardContent("content");
    }

    @Test
    void register() {
//        stubbing -> 목객체의 행위를 정의한다. (insert메소드를 정의)
        doNothing().when(boardMapper).insert(any(BoardDto.class));

//      stubbing한 메소드가 실행될 수 있는 상황을 만들어준다.
        boardService.register(boardDto);

//        검증
        verify(boardMapper, times(1)).insert(any(BoardDto.class));
    }

    @Test
    void remove() {
        doNothing().when(boardMapper).delete(any(Long.class));
        boardService.remove(1L);
        verify(boardMapper, times(1)).delete(any(Long.class));
    }

//    @Test
//    void modify() {
//        doNothing().when(boardMapper).update(any(BoardDto.class));
//        boardService.modify(new BoardDto());
//        verify(boardMapper, times(1)).update(any(BoardDto.class));
//    }

    @Test
    void find() {
//        1. stubbing
        doReturn(boardVo).when(boardMapper).select(any(Long.class));

//        2. 실행 상황
        BoardVo foundBoard = boardService.find(1L);

//        3. 검증
        assertThat(foundBoard.getBoardTitle()).isEqualTo(boardVo.getBoardTitle());
    }

    @Test
    void findException(){
//        1. stubbing
        doReturn(null).when(boardMapper).select(any(Long.class));

        assertThatThrownBy( () -> boardService.find(1L) )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지");
    }

//    @Test
//    void findAll() {
//        doReturn(List.of(new BoardVo(), new BoardVo())).when(boardMapper).selectAll();
//        List<BoardVo> boardVoList = boardService.findAll();
//        assertThat(boardVoList.size()).isEqualTo(2);
//    }
}