package com.example.app.mapper;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.dto.UserDto;
import com.example.app.domain.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    UserMapper userMapper;

    BoardDto boardDto;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        boardDto = new BoardDto();
        boardDto.setBoardTitle("ㅎㅇ");
        boardDto.setBoardContent("안녕하세요");

        userDto = new UserDto();
        userDto.setUserId("test");
        userDto.setUserPassword("1234");
        userDto.setUserGender("F");
        userDto.setUserEmail("test@naver.com");
        userDto.setUserAddress("서울시");

        userMapper.insert(userDto);
        boardDto.setUserNumber(userDto.getUserNumber());

        boardMapper.insert(boardDto);
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        boardMapper.delete(boardDto.getBoardNumber());

        BoardVo boardVo = boardMapper.select(boardDto.getBoardNumber());

        assertThat(boardVo).isNull();
    }

    @Test
    void update() {
        boardDto.setBoardTitle("update test");

        boardMapper.update(boardDto);

        BoardVo boardVo = boardMapper.select(boardDto.getBoardNumber());

        assertThat(boardVo.getBoardTitle()).isEqualTo("update test");
    }

    @Test
    void select() {
        BoardVo boardVo = boardMapper.select(boardDto.getBoardNumber());

        assertThat(boardVo.getUserId()).isEqualTo(userDto.getUserId());
    }

//    @Test
//    void selectAll() {
//        List<BoardVo> boardVos = boardMapper.selectAll();
//
//        assertThat(boardVos.size()).isNotEqualTo(0);
//    }
}










