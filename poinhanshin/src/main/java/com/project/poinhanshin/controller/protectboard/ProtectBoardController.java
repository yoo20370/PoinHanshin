package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.domain.etc.PageHandler1;
import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.service.protectboard.ProtectBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/protectboard")
public class ProtectBoardController {

    ProtectBoardService protectBoardService;

    @Autowired
    public ProtectBoardController(ProtectBoardService protectBoardService) {
        this.protectBoardService = protectBoardService;
    }

    // 임보자 공고 리스트
    @GetMapping("/list")
    public String ProtectBoardList(PageHandler1 ph , SearchCondition1 sc , Model m){

        List<ProtectBoardDto> list = protectBoardService.bringBoardList();
        int totalCnt = protectBoardService.searchListCnt();
        System.out.println(list.toString());
        System.out.println("실행됨3");
        m.addAttribute("list",list);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute(m.addAttribute("ph" , ph));
        return "protect/protecterlist";
    }

    // 임보자 공고 상세화면
    @GetMapping("/read")
    public String ProtectBoardRead(String protectboardno , SearchCondition1 sc , Model m){
        // 임시 로그인
        String LoginId = "하리보";

        ProtectBoardDto protectBoardDto = protectBoardService.bringBoardOne(protectboardno);
        System.out.println(protectBoardDto.getProtectboard_id());
        if(LoginId.equals(protectBoardDto.getProtectboard_id()))
            m.addAttribute("Mode", "WRITER");

        m.addAttribute("protectDto" , protectBoardDto);
        m.addAttribute("page",sc.getPage());
        m.addAttribute("pageSize",sc.getPageSize());

        return "protect/protecter";
    }

    // 임보자 공고 작성화면
    @GetMapping("/write")
    public String ProtectBoardWritePage(){
        return "protect/protecterreg";
    }
    @PostMapping("/write")
    public String ProtectBoardWrite(){
        return "redirect:/protectboard/write";
    }
    @GetMapping("/modify")
    public String ProtectBoardModifyPage(){
        return "protecteredit";
    }

    @PostMapping("/modify")
    public String ProtectBoardModify(){
        return "";
    }

    @PostMapping("/remove")
    public String ProtectBoardRemove(){
        return "";
    }
}
