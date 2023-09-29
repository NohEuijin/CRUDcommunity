package com.example.app.service;

import com.example.app.domain.dto.UserDto;
import com.example.app.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

//    등록
    public void register(UserDto userDto){
        if (userDto == null) {
            throw new IllegalArgumentException("회원 정보가 누락됨!!!");
        }

        userMapper.insert(userDto);
    }

//    회원 번호 찾기
    public Long findUserNumber(String userId, String userPassword){
        return Optional.ofNullable(userMapper.selectUserNumber(userId, userPassword))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("일치하는 회원 정보가 없음!!!");
                } );
    }

    public int checkId(String userId){
        return userMapper.selectId(userId);
    }
}











