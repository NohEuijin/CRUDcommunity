package com.example.app.mapper;

import com.example.app.domain.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
//    삽입
    public void insert(FileDto fileDto);
//    삭제
    public void delete(Long boardNumber);
//    파일 리스트 조회
    public List<FileDto> selectList(Long boardNumber);

//    전 날 파일 목록
    public List<FileDto> selectOldList();
}
