package com.example.app.mapper;

import com.example.app.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
//    삽입
    public void insert(UserDto userDto);

//    회원 번호 조회
    public Long selectUserNumber(@Param("userId") String userId, @Param("userPassword") String userPassword);

    public int selectId(String userId);
}
