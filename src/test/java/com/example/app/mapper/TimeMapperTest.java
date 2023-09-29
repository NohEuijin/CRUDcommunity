package com.example.app.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TimeMapperTest {
    @Autowired
    TimeMapper timeMapper;

    @Test
    void getTime() {
        log.info("**** time : " + timeMapper.getTime());
    }
}