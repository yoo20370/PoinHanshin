package com.project.poinhanshin.controller.map;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.map.MapBoardDto;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.service.map.MapBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/map")
public class MapController {

    MapBoardService mapBoardService;

    @Autowired
    public MapController(MapBoardService mapBoardService) {
        this.mapBoardService = mapBoardService;
    }

    // 원 그려주는 맵으로 이동 및 게시물 데이터 전송
    @GetMapping("/oneMapMain")
    public String getMapData(SearchCondition sc , Model m , @ModelAttribute("msg") String msg , @SessionAttribute(name = "loginUser", required = false) User loginUser){

        System.out.println("oneMapMain sc : "+sc);

        m.addAttribute("loginUser" , loginUser);

        List<MapBoardDto> mapBoardDtoList = mapBoardService.getMapBoardListService(sc);

        m.addAttribute("mapBoardDtoList" , mapBoardDtoList);
        m.addAttribute("sc",sc);
        return "/map/findmapmainonemain";
    }


    @GetMapping("/mapFileterSearch")
    @ResponseBody
    public ResponseEntity<List<MapBoardDto>> getSearchedMapBoardList(SearchCondition sc){

        List<MapBoardDto> mapBoardDtoList = mapBoardService.getMapBoardListService(sc);

        System.out.println(mapBoardDtoList);
        return new ResponseEntity<List<MapBoardDto>>(mapBoardDtoList ,HttpStatus.OK);
    }
}
