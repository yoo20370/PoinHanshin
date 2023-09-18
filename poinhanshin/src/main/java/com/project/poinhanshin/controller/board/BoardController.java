package com.project.poinhanshin.controller.board;


import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.etc.PageHandler;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.service.board.BoardService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 커뮤니티 게시물 목록
    @GetMapping("/list")
    public String boardList(SearchCondition sc , Model m , @ModelAttribute("msg") String msg, @SessionAttribute(name = "loginUser", required = false) User loginUser){

        m.addAttribute("loginUser", loginUser); // 로그인 이식

        HashMap hashMap = boardService.bringBoardList(sc);
        List<BoardDto> boardDtoList = (List<BoardDto>)hashMap.get("boardDtoList");
        List<BoardDto> topBoardDtoList = (List<BoardDto>)hashMap.get("topBoardDtoList");

        int totalCnt = boardService.bringBoarndListCnt(sc);

        PageHandler ph = new PageHandler(totalCnt , sc);

        m.addAttribute("boardDtoList" , boardDtoList );
        m.addAttribute("topBoardDtoList" , topBoardDtoList);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("sc",sc);
        m.addAttribute("ph",ph);
        m.addAttribute("msg",msg);
        return "board/boardList";
    }

    // 커뮤니티 게시물 상세 페이지
    @GetMapping("/read")
    public String boardRead(Integer boardno , SearchCondition sc , Model m , @ModelAttribute("msg") String msg, @SessionAttribute(name = "loginUser", required = false) User loginUser){

        m.addAttribute("loginUser", loginUser);

        // 임시 로그인
        Integer LoginId = 1;

        BoardDto boardDto = boardService.bringBoardOne(boardno);

        // 로그인 아이디와 작성자가 같은 경우 Mode WRITER
        if(LoginId.equals(boardDto.getBoard_userno()))
            m.addAttribute("WriterCheck", "OK");

        m.addAttribute("LoginId", LoginId);
        m.addAttribute("boardDto" , boardDto);
        m.addAttribute("sc",sc);
        m.addAttribute("msg" , msg);
        return "board/board";
    }

    // 커뮤니티 게시물 작성 상세 페이지
    @GetMapping("/write")
    public String boardWritePage(SearchCondition sc , Model m, RedirectAttributes redirectAttributes , @ModelAttribute("msg") String msg, @SessionAttribute(name = "loginUser", required = false) User loginUser){

        m.addAttribute("loginUser", loginUser);

        Integer LoginId = 1;

        if(LoginId == null) {
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/protectboard/list";
        }

        m.addAttribute("sc",sc);
        m.addAttribute("LoginId", LoginId);
        m.addAttribute("msg" , msg);

        return "board/boardreg";
    }

    // 커뮤니티 등록 페이지에서 ajax로 보낸 데이터를 받아 각 변수에 바인딩하고 이를 DB에 저장
    // 커뮤니티 게시물 작성 데이터 DB에 저장
    @PostMapping("/write")
    @ResponseBody
    public ResponseEntity<Integer> boardWrite(@RequestParam(required = false) Integer board_userno , @RequestParam String board_title ,
                                              @RequestParam String board_content , @RequestParam Boolean board_ani_category ,
                                              @RequestParam(required = false) List<MultipartFile> boardFile) throws IOException {
        BoardDto boardDto = new BoardDto(board_userno , null , board_title , board_content , board_ani_category , null , 0 , 0 , 0 ,0);

        boardDto.setBoardFile(boardFile);
        // 파일이 있는 경우
        if(boardFile != null){
            boardDto.setFileAttached(1);
            System.out.println(boardFile);
        }

        System.out.println(boardDto);
        int result = boardService.writeContent(boardDto);

        return new ResponseEntity<Integer>( result , HttpStatus.OK);
    }

    // 커뮤니티 게시물 수정 상세 페이지
    @GetMapping("/modify")
    public String boardModifyPage(Integer boardno , SearchCondition sc , Model m , RedirectAttributes redirectAttributes, @SessionAttribute(name = "loginUser", required = false) User loginUser){

        m.addAttribute("loginUser", loginUser);

        // 임시 로그인
        Integer LoginId = 1;

        // 로그인 확인 (나중에 loginUser == null로 변경
        if(LoginId == null) {
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/protectboard/list";
        }
        BoardDto boardDto = boardService.bringBoardOne(boardno);

        System.out.println(boardno);
        m.addAttribute("LoginId" ,LoginId);
        m.addAttribute("boardDto" , boardDto);
        m.addAttribute("sc",sc);
        return "board/boardedit";
    }

    // 커뮤니티 수정 페이제에서 ajax로 보낸 데이터를 받아 각 변수게 바인딩하고 이를 DB에 저장
    @PostMapping("/modify")
    public ResponseEntity<Integer> boardModify(@RequestParam Integer board_userno , @RequestParam Integer boardno, @RequestParam String board_title ,
                                               @RequestParam String board_content , @RequestParam Boolean board_ani_category , @RequestParam Integer fileAttached,
                                               @RequestParam(required = false) List<MultipartFile> boardFile
                                                ) throws IOException {
        BoardDto boardDto = new BoardDto(board_userno , boardno , board_title , board_content , board_ani_category , null , null , null , null , fileAttached );
        boardDto.setBoardFile(boardFile);

        // Login 연결 시 수정 필요
        boardService.modifyContent(boardDto , board_userno);
        return new ResponseEntity<Integer>(boardno , HttpStatus.OK);
    }

    // 커뮤니티 게시물 삭제
    @PostMapping("/remove")
    public String boardRemove(Integer boardno , SearchCondition sc , RedirectAttributes redirectAttributes ) throws IOException {

        // 임시 로그인
        Integer loginId = 1;

        // 로그인 여부 확인
        if(loginId == null){
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/board/list";
        }

        // 삭제 실패시
        if(boardService.removeContent(boardno , loginId) != 1){
            redirectAttributes.addAttribute("page" , sc.getPage());
            redirectAttributes.addAttribute("pageSize" , sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addAttribute("boardno",boardno);
            redirectAttributes.addFlashAttribute("msg", "FAIL_REMOVE");
            return "redirect:/protectboard/read";
        }

        redirectAttributes.addFlashAttribute("msg", "SUCCESS_REMOVE");
        return "redirect:/board/list";
    }
}
