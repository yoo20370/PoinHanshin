package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtectBoardFileMapper {

    // 임보자 게시물에 해당하는 이미지들을 가져오는 메서드
    List<ProtectBoardFileDto> selectFiles(Integer protectboardno);

    int insertFiles(ProtectBoardFileDto protectBoardFileDto);

    int deleteFile(Integer protectboardfileno );

    int selectCnt(Integer protectboardfileno);

}
