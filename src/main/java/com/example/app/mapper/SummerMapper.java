package com.example.app.mapper;

import com.example.app.domain.dto.SummerDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SummerMapper {
    public SummerDto select(Long summerNumber);
    public void insert(SummerDto summerDto);
}
