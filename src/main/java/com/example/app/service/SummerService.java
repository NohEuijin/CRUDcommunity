package com.example.app.service;

import com.example.app.domain.dto.SummerDto;
import com.example.app.mapper.SummerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummerService {
    private final SummerMapper summerMapper;

    public void register(SummerDto summerDto){
        summerMapper.insert(summerDto);
    }

    public SummerDto find(Long summerNumber){
        return summerMapper.select(summerNumber);
    }
}













