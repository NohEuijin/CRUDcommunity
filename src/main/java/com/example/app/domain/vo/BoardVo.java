package com.example.app.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class BoardVo {
    private Long boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Long userNumber;
    private String userId;
    private String fileName;
    private String fileUuid;
    private String fileUploadPath;
}
