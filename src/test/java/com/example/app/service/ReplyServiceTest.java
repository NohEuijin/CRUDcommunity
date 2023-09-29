package com.example.app.service;

import com.example.app.domain.dto.ReplyDto;
import com.example.app.domain.vo.ReplyVo;
import com.example.app.mapper.ReplyMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReplyServiceTest {
    @Mock
    ReplyMapper replyMapper;

    @InjectMocks
    ReplyService replyService;

    ReplyVo replyVo;

    @BeforeEach
    void setUp(){
        replyVo = new ReplyVo();
        replyVo.setReplyContent("test");
    }

    @Test
    void register() {
        doNothing().when(replyMapper).insert(any(ReplyDto.class));
        replyService.register(new ReplyDto());
        verify(replyMapper, times(1)).insert(any(ReplyDto.class));
    }

    @Test
    void findList() {
        doReturn(List.of(new ReplyVo(), new ReplyVo())).when(replyMapper).selectList(any(Long.class));
        List<ReplyVo> foundList = replyService.findList(1L);
        assertThat(foundList.size()).isEqualTo(2);
    }

    @Test
    void find() {
        doReturn(replyVo).when(replyMapper).select(any(Long.class));
        ReplyVo foundReply = replyService.find(1L);
        assertThat(foundReply.getReplyContent()).isEqualTo("test");
    }

    @Test
    void modify() {
        doNothing().when(replyMapper).update(any(ReplyDto.class));
        replyService.modify(new ReplyDto());
        verify(replyMapper, times(1)).update(any(ReplyDto.class));
    }

    @Test
    void remove() {
        doNothing().when(replyMapper).delete(any(Long.class));
        replyService.remove(1L);
        verify(replyMapper, times(1)).delete(any(Long.class));
    }
}