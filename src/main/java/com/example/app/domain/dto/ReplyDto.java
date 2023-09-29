package com.example.app.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ReplyDto {
    private Long replyNumber;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long boardNumber;
    private Long userNumber;
}
