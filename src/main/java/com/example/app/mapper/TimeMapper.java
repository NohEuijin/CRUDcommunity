package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
//    Mapper.xml의 쿼리 id와 일치하는 메소드 이름을 사용하면 알아서 매핑된다.
    public String getTime();

    @Select("SELECT SYSDATE FROM DUAL")
    public String getTime2();
}
