package com.project.poinhanshin.controller.board;

import com.project.poinhanshin.service.board.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller()
public class LikeController {

    LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/likeTest")
    public String likeTest(Model m){
        likeService.likeFunction(1, 1);
        return "test/likeDBTest";
    }

}
