package com.example.app.mapper;

import com.example.app.domain.dto.ReplyDto;
import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
//    삽입
    public void insert(ReplyDto replyDto);
//    리스트 조회
    public List<ReplyVo> selectList(Long boardNumber);
//    단건 조회
    public ReplyVo select(Long replyNumber);
//    수정
    public void update(ReplyDto replyDto);
//    삭제
    public void delete(Long replyNumber);
//    리스트 조회(페이징 처리)
    public List<ReplyVo> selectListPage(@Param("criteria") Criteria criteria, @Param("boardNumber") Long boardNumber);
//    리플 수 조회
    public int selectTotal(Long boardNumber);
}








