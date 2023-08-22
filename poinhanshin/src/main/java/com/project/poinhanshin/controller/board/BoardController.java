package com.project.poinhanshin.controller.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.etc.PageHandler;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String board(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model, Integer page, Integer pageSize, SearchCondition sc) {
        model.addAttribute("user", loginUser);

        if (page==null) page=1;
        if (pageSize==null) pageSize=50;

        if (sc.getOption().equals("T") || sc.getOption().equals("W") || sc.getOption().equals("A")) {
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
            Map<String, Integer> map = new HashMap<>();
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);
            List<BoardDto> list = boardService.getSearchResultPage(sc);
            model.addAttribute("list", list);
            model.addAttribute("ph", pageHandler);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
            return "board/boardList";
        }

        int totalCnt = boardService.getCount();
        PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

        Map<String, Integer> map = new HashMap<>();
        map.put("offset", (page - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<BoardDto> list = boardService.getPage(map);
        model.addAttribute("list", list);
        model.addAttribute("ph", pageHandler);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "boardList";
    }
    /*@GetMapping("/list")
    public String board(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model, SearchCondition sc) {
        model.addAttribute("user", loginUser);

        if (sc.getPage()==null) sc.setPage(1);
        if (sc.getPageSize()==null) sc.setPageSize(50);

        if (sc.getOption().equals("T") || sc.getOption().equals("W") || sc.getOption().equals("A")) {
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            Map<String, Integer> map = new HashMap<>();
            map.put("offset", (sc.getPage() - 1) * sc.getPageSize());
            map.put("pageSize", sc.getPageSize());
            model.addAttribute("list", list);
            model.addAttribute("ph", ph);
            model.addAttribute("sc",sc);
            return "board/boardList";
        }

        int totalCnt = boardService.getCount();
        PageHandler ph = new PageHandler(totalCnt, sc);

        Map<String, Integer> map = new HashMap<>();
        map.put("offset", (sc.getPage() - 1) * sc.getPageSize());
        map.put("pageSize", sc.getPageSize());

        List<BoardDto> list = boardService.getPage(map);
        model.addAttribute("list", list);
        model.addAttribute("ph", ph);
        model.addAttribute("sc",sc);

        return "boardList";
    }*/

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model model,
                       @SessionAttribute(name = "loginUser", required = false) User loginUser) {
        BoardDto boardDto = boardService.read(bno);
        model.addAttribute("user", loginUser);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        return "boardR";
    }

    @GetMapping("/write")
    public String write(@SessionAttribute(name = "loginUser", required = false) User loginUser,
                        @ModelAttribute("boardDto") BoardDto boardDto, Model model, RedirectAttributes redirectAttributes) {

        if (loginUser==null) {
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/login";
        }

        model.addAttribute("boardDto", boardDto);
        model.addAttribute("user", loginUser);
        return "boardC";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = "loginUser", required = false) User loginUser,
                        BoardDto boardDto, RedirectAttributes redirectAttributes) {
        boardDto.setWriter(loginUser.getName());
        boardService.write(boardDto);
        redirectAttributes.addFlashAttribute("msg", "WRT_OK");
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize,
                         Model model, RedirectAttributes redirectAttributes,
                         @SessionAttribute(name = "loginUser", required = false) User loginUser,
                         BoardDto boardDto) {

        model.addAttribute("page",page);
        model.addAttribute("pageSize",pageSize);

        if (loginUser==null || !loginUser.getName().equals(boardDto.getWriter())) { //BoardDto 에 loginId 를 구현 안해놔서 동명이인이면 게시글을 지울수가 있음
            redirectAttributes.addFlashAttribute("msg", "NOT_EQL");
            return "redirect:/board/list";
        }
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("msg", "REMOVE_OK");
        return "redirect:/board/list";

    }

    @GetMapping("/modify")
    public String modify(Integer bno, @SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        BoardDto boardDto = boardService.readV2(bno);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("user", loginUser);
        return "boardU";
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, RedirectAttributes redirectAttributes) {
        boardDto.setWriter(boardDto.getWriter());
        boardService.modify(boardDto);
        redirectAttributes.addFlashAttribute("msg", "MOD_OK");
        return "redirect:/board/list";

    }



}
