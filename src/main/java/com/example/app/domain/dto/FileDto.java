package com.example.app.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FileDto {
    private Long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long boardNumber;
}










