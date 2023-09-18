package com.project.poinhanshin.controller.mbti;

import com.project.poinhanshin.domain.api.Abandoned_animal;

import com.project.poinhanshin.etc.ApiExplorer;
import com.project.poinhanshin.mapper.mbti.MBTIMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/MBTI")
public class MBTIController {

    ApiExplorer apiExplorer;
    MBTIMapper mbtiMapper;

    @Autowired
    public MBTIController(ApiExplorer apiExplorer, MBTIMapper mbtiMapper) {
        this.apiExplorer = apiExplorer;
        this.mbtiMapper = mbtiMapper;
    }

    /*@Autowired
    public MBTIController(ApiExplorer apiExplorer) {
        this.apiExplorer = apiExplorer;
    }*/

    @GetMapping("/main")
    public String mbti(Model m) throws IOException, ParseException {
        Abandoned_animal abandoned_animal[] = apiExplorer.SearchAnimalList("","","","","","","","","","1","6");

        m.addAttribute("AAArr",abandoned_animal);
        return "mbti/mbti_main";
    }

    @GetMapping("/start")
    public String mbtiStart() {
        return "mbti/mbti";
    }

    @GetMapping("/result")
    public String mbtiSearch(String mbti , Model m){
        m.addAttribute("MBTI",mbti);
        return "mbti/mbti_result";
    }

    @GetMapping("/test")
    public String mbtitest() {
        System.out.println(mbtiMapper.searchAband(2));
        return "test/likeDBTest";
    }

}
