package com.project.poinhanshin.service.mbti;

import com.project.poinhanshin.domain.mbti.MBTInameKind;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MBTIService {
    List<MBTInameKind> serviceSearchAband(Integer MBTI);
}
