package com.project.poinhanshin.mapper.mbti;

import com.project.poinhanshin.domain.mbti.MBTInameKind;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MBTIMapper {
    MBTInameKind searchAband(Integer MBTI);
}
