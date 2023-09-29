package com.example.app.mapper;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.dto.ReplyDto;
import com.example.app.domain.dto.UserDto;
import com.example.app.domain.vo.ReplyVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class ReplyMapperTest {
    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BoardMapper boardMapper;

    ReplyDto replyDto;
    BoardDto boardDto;
    UserDto userDto;

    @BeforeEach
    void setUp() {

        userDto = new UserDto();
        userDto.setUserId("test");
        userDto.setUserEmail("test@naver.com");
        userDto.setUserAddress("test");
        userDto.setUserGender("F");
        userDto.setUserPassword("1234");

        userMapper.insert(userDto);

        boardDto = new BoardDto();
        boardDto.setBoardTitle("test");
        boardDto.setBoardContent("test");
        boardDto.setUserNumber(userDto.getUserNumber());

        boardMapper.insert(boardDto);

        replyDto = new ReplyDto();
        replyDto.setReplyContent("test 한당!");
        replyDto.setBoardNumber(boardDto.getBoardNumber());
        replyDto.setUserNumber(userDto.getUserNumber());

        replyMapper.insert(replyDto);
    }

    @Test
    void selectList() {
        List<ReplyVo> replyVoList = replyMapper.selectList(boardDto.getBoardNumber());
        assertThat(replyVoList.size()).isNotEqualTo(0);
    }

    @Test
    void select() {
        ReplyVo replyVo = replyMapper.select(replyDto.getReplyNumber());
        assertThat(replyVo.getReplyContent()).isEqualTo(replyDto.getReplyContent());
    }

    @Test
    void update() {
        replyDto.setReplyContent("update");
        replyMapper.update(replyDto);
        ReplyVo foundReply = replyMapper.select(replyDto.getReplyNumber());

        assertThat(foundReply.getReplyContent()).isEqualTo("update");
    }

    @Test
    void delete() {
        replyMapper.delete(replyDto.getReplyNumber());
        ReplyVo foundReply = replyMapper.select(replyDto.getReplyNumber());

        assertThat(foundReply).isNull();
    }
}










