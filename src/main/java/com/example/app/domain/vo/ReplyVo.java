package com.example.app.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ReplyVo {
    private Long replyNumber;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long boardNumber;
    private Long userNumber;
    private String userId;
}
