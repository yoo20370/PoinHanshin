package com.project.poinhanshin.service.map;

import com.project.poinhanshin.domain.map.MapBoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapBoardService {
    List<MapBoardDto> SelectMyMapService(Integer userno);
}
