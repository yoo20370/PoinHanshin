package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.service.protectboard.ProtectBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/protectboard")
public class ProtectBoardController {

    ProtectBoardService protectBoardService;

    @Autowired
    public ProtectBoardController(ProtectBoardService protectBoardService) {
        this.protectBoardService = protectBoardService;
    }

    // 임보자 공고 리스트
    @GetMapping("/")
    public String ProtectBoardList(){

        return "protect/protecterlist";
    }

    // 임보자 공고 상세화면
    @GetMapping("/read")
    public String ProtectBoardRead(){
        return "";
    }

    // 임보자 공고 작성화면
    @GetMapping("/write")
    public String ProtectBoardWritePage(){
        return "";
    }
    @PostMapping("/write")
    public String ProtectBoardWrite(){
        return "redirect:/protectboard/write";
    }
    @GetMapping("/modify")
    public String ProtectBoardModifyPage(){
        return "";
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
