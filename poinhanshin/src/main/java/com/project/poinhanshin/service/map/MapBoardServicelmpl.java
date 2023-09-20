package com.project.poinhanshin.service.map;

import com.project.poinhanshin.domain.map.MapBoardDto;
import com.project.poinhanshin.mapper.map.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapBoardServicelmpl implements MapBoardService{
    MapMapper mapMapper;

    @Autowired
    public MapBoardServicelmpl( MapMapper mapMapper) {
        this.mapMapper = mapMapper;
    }

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    @Override
    public List<MapBoardDto> SelectMyMapService(Integer userno) {
        List<MapBoardDto> SelectMyMapService = mapMapper.SelectMyMap(userno);
        return SelectMyMapService;
    }


    // 마이페이지 - 자신이 작성한 실종/발견 지도 게시글 불러오기
    @Override
    public List<MapBoardDto> WriteMyMapService(Integer userno) {
        List<MapBoardDto> WriteMyMapService = mapMapper.WriteMyMap(userno);
        return WriteMyMapService;
    }
}
