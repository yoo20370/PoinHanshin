package com.project.poinhanshin.controller.mbti;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/information")
public class MBTIController {
    @GetMapping("/MBTI")
    public String mbti() {
        return "mbti/mbti_main";
    }

    @GetMapping("/MBTI/start")
    public String mbtiStart() {
        return "mbti/mbti";
    }
}
