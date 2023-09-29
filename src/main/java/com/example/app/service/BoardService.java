package com.example.app.service;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import com.example.app.domain.vo.Criteria;
import com.example.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;

    //    추가
    public void register(BoardDto boardDto){
        boardMapper.insert(boardDto);
    }

    //    삭제
    public void remove(Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시판 번호 누락!!");
        }

        fileService.remove(boardNumber);
        boardMapper.delete(boardNumber);
    }

    //    수정
    public void modify(BoardDto boardDto, List<MultipartFile> files){
        if (!files.isEmpty() && !files.get(0).isEmpty()) {
            fileService.remove(boardDto.getBoardNumber());
            try {
                fileService.registerAndSaveFile(files, boardDto.getBoardNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boardMapper.update(boardDto);
    }

    //    조회
    public BoardVo find(Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시판 번호 누락!!");
        }

        return Optional.ofNullable(boardMapper.select(boardNumber))
                .orElseThrow(() -> { throw new IllegalArgumentException("존재하지 않는 회원 번호!"); });
    }

    //    전체 조회
    public List<BoardVo> findAll(Criteria criteria){
        return boardMapper.selectAll(criteria);
    }

//    전체 게시물 수 조회
    public int getTotal(){
        return boardMapper.selectTotal();
    }


//    게시물 작성 최종(파일처리 포함)
    public void registerAndFileProc(BoardDto boardDto, List<MultipartFile> files){
        register(boardDto);

        if(files.isEmpty()) { return; }

        try {
            fileService.registerAndSaveFile(files, boardDto.getBoardNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}















