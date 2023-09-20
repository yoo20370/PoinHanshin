package com.project.poinhanshin.service.map;

import com.project.poinhanshin.domain.map.MapBoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapBoardService {

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    List<MapBoardDto> SelectMyMapService(Integer userno);

    // 마이페이지 - 자신이 작성한 실종/발견 지도 게시글 불러오기
    List<MapBoardDto> WriteMyMapService(Integer userno);
}
