package com.example.app.controller;

import com.example.app.domain.dto.ReplyDto;
import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.PageVo;
import com.example.app.domain.vo.ReplyVo;
import com.example.app.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

//    Rest컨트롤러에서 json형식의 데이터 받을 준비를 한다.
//    value : url경로 (다른 옵션을 사용하지 않는다면 value를 생략하고 값만 넣으면 된다.)
//    consumes : 우리가 받는 데이터의 형식
//    produces : 우리가 보내는 데이터의 형식
//    @PostMapping(value = "/write", consumes = "application/json", produces = "text/plain; charset=utf-8")
//    public ResponseEntity<String> replyAdd(@RequestBody ReplyDto replyDto) throws UnsupportedEncodingException {
//        replyService.register(replyDto);
//        return new ResponseEntity<>(new String("작성 성공!".getBytes(), "utf-8"), HttpStatus.OK);
//    }

    @PostMapping("")
    public String replyAdd(@RequestBody ReplyDto replyDto){
//        RequestBody는 json형식의 데이터를 자동으로 객체 필드에 매핑시켜준다.

        replyService.register(replyDto);
        return "작성 성공!";
    }

    @GetMapping("/list/{boardNumber}")
    public List<ReplyVo> showList(@PathVariable("boardNumber") Long boardNumber){
//        url로 데이터를 넘겨받아 조회한다.
//        url경로상의 데이터를 받기 위해서는 @PathVariable 어노테이션을 사용한다.
        return replyService.findList(boardNumber);
    }

//    수정 처리를 위한 method
//    1. Patch : 일부 수정
//    2. Put : 전체 수정
//    위와 가타이 나누어 사용하지만 크게 구분하지 않는 경우도 있다.
    @PatchMapping("/{replyNumber}")
    public void modify(@PathVariable("replyNumber")Long replyNumber,
                       @RequestBody ReplyDto replyDto){

        replyDto.setReplyNumber(replyNumber);

        replyService.modify(replyDto);
    }

//    @PatchMapping(value = {"/{replyNumber}", "/{replyNumber}/{replyContent}"})
//    public void modify(@PathVariable("replyNumber")Long replyNumber,
//                       @PathVariable(value = "replyContent", required = false) String replyContent){
//
//        ReplyDto replyDto = new ReplyDto();
//        replyDto.setReplyNumber(replyNumber);
//        replyDto.setReplyContent(replyContent);
//
//        replyService.modify(replyDto);
//    }

   @DeleteMapping("/{replyNumber}")
   public void replyRemove(@PathVariable("replyNumber") Long a){
        replyService.remove(a);
   }

   @GetMapping("/list/{boardNumber}/{page}")
    public Map<String, Object> replyListPage(@PathVariable("boardNumber")Long boardNumber, @PathVariable("page")Integer page){
       Criteria criteria = new Criteria();
       criteria.setPage(page);

       PageVo pageVo = new PageVo(replyService.getTotal(boardNumber), criteria);
       List<ReplyVo> replyVoList = replyService.findListPage(criteria, boardNumber);

       Map<String, Object> replyMap = new HashMap<>();
       replyMap.put("pageVo", pageVo);
       replyMap.put("replyList", replyVoList);

       return replyMap;
   }

}









