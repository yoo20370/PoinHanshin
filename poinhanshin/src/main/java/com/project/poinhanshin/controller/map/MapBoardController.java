package com.project.poinhanshin.controller.map;

import com.project.poinhanshin.domain.etc.PageHandler;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/map")
public class MapBoardController {

    MapBoardService mapBoardService;

    @Autowired
    public MapBoardController(MapBoardService mapBoardService) {
        this.mapBoardService = mapBoardService;
    }

    // 메인 페이지 호출
    @GetMapping("/main")
    public String goToMapMain(@SessionAttribute(name = "loginUser", required = false) User loginUser){

        return "/map/findmapmainonemain";
    }

    // 맵 게시물 목록 불러오기
    @GetMapping("/list")
    public String getMapBoardList(SearchCondition sc , Model m , @ModelAttribute("msg") String msg,  @SessionAttribute(name = "loginUser", required = false) User loginUser ){

        // 로그인 여부
        if(loginUser != null){
            // 로그인 정보 전달
            m.addAttribute("loginUser" , loginUser);
        }

        List<MapBoardDto> mapBoardDtoList = mapBoardService.getMapBoardListService(sc);

        System.out.println(mapBoardDtoList);

        int totalCnt = mapBoardService.getMapBoardListCnt(sc);

        PageHandler ph = new PageHandler(totalCnt , sc);

        m.addAttribute("mapBoardDtoList",mapBoardDtoList);
        m.addAttribute("totalCnt" , totalCnt);
        m.addAttribute("sc", sc);
        m.addAttribute("ph" ,ph);
        m.addAttribute("msg" , msg);
        return "/map/findmaplist";
    }

    // 맵 보드 게시물 상세 페이지
    @GetMapping("/read")
    public String getMapBoardOne(Integer mapboardno , SearchCondition sc, Model m , @ModelAttribute("msg") String msg  , @SessionAttribute(name = "loginUser", required = false) User loginUser ){

        m.addAttribute("loginUser" , loginUser);

        MapBoardDto mapBoardDto = mapBoardService.bringMapBoardOne(mapboardno);

        if(loginUser != null){
            if(loginUser.getUserno().equals(mapBoardDto.getMapboard_userno().longValue()))
                m.addAttribute("WriterCheck", "OK");
        }

        m.addAttribute("mapBoardDto" , mapBoardDto);
        m.addAttribute("sc",sc);
        m.addAttribute("msg",msg);
        return "/map/findmaplistdetail";
    }

    @GetMapping("/write")
    public String mapboardWrite(SearchCondition sc, Model m , RedirectAttributes redirectAttributes , @ModelAttribute("msg") String msg, @SessionAttribute(name = "loginUser", required = false) User loginUser ){

        m.addAttribute("loginUser" , loginUser);

        // 로그인 여부 체크
        /*if(loginUser == null){
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/map/read";
        }*/

        m.addAttribute("sc", sc);
        m.addAttribute("msg",msg);

        return "/map/findmapreg";
    }

    @PostMapping("/write")
    @ResponseBody
    public ResponseEntity<String> mapBoardWrite(@RequestParam Boolean mapboard_ani_category , @RequestParam Boolean writertype , @RequestParam String mapboard_title , @RequestParam String mapboard_content , @RequestParam Date missingtime , @RequestParam String missingAddress , @RequestParam(required = false) List<MultipartFile> mapBoardFile , @RequestParam Integer loginUser )
    throws IOException {

        MapBoardDto mapBoardDto = new MapBoardDto("" , loginUser , null , mapboard_title , mapboard_content , missingtime , missingAddress , 37.19357  , 127.0227 , new Date() , 0 , mapboard_ani_category , writertype ,0 );
        System.out.println(mapBoardDto);
        mapBoardDto.setMapBoardFile(mapBoardFile);

        System.out.println("Post , wirite -mapBoardDto : "+mapBoardDto);

        // 이미지가 없는 경우
        if(mapBoardFile != null){
            mapBoardDto.setFileAttached(0);
        }

        int result = mapBoardService.writeMapBoard(mapBoardDto);
        System.out.println(result);
        return new ResponseEntity<String>(HttpStatus.OK);
    }



    /*@PostMapping("/remove")
    public String mapBoard*/
}
