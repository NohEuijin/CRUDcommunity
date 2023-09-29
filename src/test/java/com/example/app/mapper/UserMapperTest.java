package com.example.app.mapper;

import com.example.app.domain.dto.UserDto;
import com.example.app.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("test");
        userDto.setUserEmail("test@naver.com");
        userDto.setUserGender("M");
        userDto.setUserAddress("서울시");
        userDto.setUserPassword("1234");
    }

    @Test
    void insert() {
        userMapper.insert(userDto);
    }

    @Test
    void selectUserNumber() {
        userMapper.selectUserNumber("test", "1234");
    }

    @Test
    void insertAndSelect(){
//        Assertions.assertThat(1).isEqualTo(2);

        userMapper.insert(userDto);

        Long userNumber = userDto.getUserNumber();
        Long foundUserNumber = userMapper.selectUserNumber(userDto.getUserId(), userDto.getUserPassword());

        log.info("userDto : {}", userDto.toString());

        Assertions.assertThat(userNumber).isEqualTo(foundUserNumber);
    }

    @Test
    void aa(){
        UserDto user = new UserDto();

        bb(user);

        log.info("user : {}", user.toString());
    }


    void bb(UserDto user){
        user.setUserNumber(10L);
    }

    @Test
    void stringTest(){
        String param = "안녕하세요! 반갑습니다.";
        Assertions.assertThat(param)
                .contains("반갑")
                .startsWith("안")
                .isNotNull();
    }

    void mapTest(){
        Criteria criteria = new Criteria();
        Long boardNumber = 3L;

        Map<String, Object> map = new HashMap<>();
        map.put("criteria", criteria);
        map.put("boardNumber", boardNumber);

        ((Criteria)map.get("criteria")).getAmount();
    }
}













