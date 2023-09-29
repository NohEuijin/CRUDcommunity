package com.example.app.service;

import com.example.app.domain.dto.ReplyDto;
import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.ReplyVo;
import com.example.app.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyMapper replyMapper;

    //    삽입
    public void register(ReplyDto replyDto){
        replyMapper.insert(replyDto);
    }

    //    리스트 조회
    public List<ReplyVo> findList(Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락!!");
        }
        return replyMapper.selectList(boardNumber);
    }
    //    단건 조회
    public ReplyVo find(Long replyNumber){
        if (replyNumber == null) {
            throw new IllegalArgumentException("댓글 번호 누락!!!");
        }
        return Optional.ofNullable(replyMapper.select(replyNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 댓글!!"); });
    }
    //    수정
    public void modify(ReplyDto replyDto){
        replyMapper.update(replyDto);
    }
    //    삭제
    public void remove(Long replyNumber){
        if (replyNumber == null) {
            throw new IllegalArgumentException("댓글 번호 누락!!");
        }

        replyMapper.delete(replyNumber);
    }

    //    리스트 조회(페이징 처리)
    public List<ReplyVo> findListPage(Criteria criteria, Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락!!!");
        }

        return replyMapper.selectListPage(criteria, boardNumber);
    }

    //    리플 수 조회
    public int getTotal(Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락!!!");
        }

        return replyMapper.selectTotal(boardNumber);
    }
}















