package com.project.poinhanshin.controller.mbti;

import com.project.poinhanshin.domain.api.Abandoned_animal;
import com.project.poinhanshin.domain.etc.PageHandler1;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.etc.ApiExplorer;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class MBTIController {

    ApiExplorer apiExplorer;

    @Autowired
    public MBTIController(ApiExplorer apiExplorer) {
        this.apiExplorer = apiExplorer;
    }

    @GetMapping("/MBTI")
    public String mbti(SearchCondition1 sc, Model m) throws IOException, ParseException {
        if(sc.getPage() == null); sc.setPage(1);
        Abandoned_animal abandoned_animal[] = apiExplorer.SearchAnimalList("","","","","","","","","",sc.getPage().toString(),"8");

        int totalCnt = Integer.parseInt(abandoned_animal[0].getTotalCount());
        PageHandler1 ph = new PageHandler1(totalCnt , sc);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("ph",ph);
        m.addAttribute("AAArr",abandoned_animal);
        return "mbti/mbti_main";
    }

    @GetMapping("/MBTI/start")
    public String mbtiStart() {
        return "mbti/mbti";
    }
}
