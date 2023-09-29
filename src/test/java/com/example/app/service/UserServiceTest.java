package com.example.app.service;

import com.example.app.domain.dto.UserDto;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("test");
        userDto.setUserPassword("1234");
        userDto.setUserEmail("test@naver.com");
        userDto.setUserGender("M");
        userDto.setUserAddress("서울시");
    }


    @Test
    void registerAndFindUserNumber() {
        userService.register(userDto);

        Long foundNumber = userService.findUserNumber(userDto.getUserId(), userDto.getUserPassword());

        Assertions.assertThat(foundNumber).isEqualTo(userDto.getUserNumber());
    }

}










