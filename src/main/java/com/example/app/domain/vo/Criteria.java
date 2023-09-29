package com.example.app.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Criteria {
    private int page;   // 현재 페이지
    private int amount; // 한 페이지 당 게시물 수

    public Criteria() {
        this.page = 1;
        this.amount = 12;
    }
}
