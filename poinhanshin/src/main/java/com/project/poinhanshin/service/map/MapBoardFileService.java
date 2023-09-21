package com.project.poinhanshin.service.map;

import com.project.poinhanshin.domain.map.MapBoardFileDto;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface MapBoardFileService {

    // 게시물에 해당하는 이미지들 가져오기
    List<MapBoardFileDto> MapBoardSelectFileService(Integer mapboardno);

    // 게시물 이미지 등록
    int MapBoardInsertFileService(MultipartFile multipartFile, Integer mapboard_userno);

    // 게시물 이미지 삭제
    int MapBoardDeleteFileService(String stored_file_name);

    // 게시물 이미지 파일 개수 카운트
    int MapBoardSelectCnt(Integer mapboardfileno);

    // 게시물 파일들 이름 반환
    List<String> MapBoardSelectFileName(Integer mapboardno);
}
