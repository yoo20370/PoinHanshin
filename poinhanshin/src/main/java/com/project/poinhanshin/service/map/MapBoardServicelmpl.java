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

    @Override
    public List<MapBoardDto> SelectMyMapService(Integer userno) {
        List<MapBoardDto> SelectMyMapService = mapMapper.SelectMyMap(userno);
        return SelectMyMapService;
    }
}
