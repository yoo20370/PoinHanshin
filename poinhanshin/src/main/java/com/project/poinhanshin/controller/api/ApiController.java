package com.project.poinhanshin.controller.api;

import com.project.poinhanshin.domain.api.Abandoned_animal;
import com.project.poinhanshin.domain.api.Shelter;
import com.project.poinhanshin.domain.etc.PageHandler1;
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
public class ApiController {
    ApiExplorer apiExplorer;

    @Autowired
    public ApiController(ApiExplorer apiExplorer) {
        this.apiExplorer = apiExplorer;
    }

    @GetMapping("/AnimalList")
    public String AnimalList(SearchCondition1 sc , String kind , Model m) throws IOException, ParseException {
        if(sc.getPage() == null) sc.setPage(1);
        if(kind == null) kind = "";
        Abandoned_animal abandoned_animal[] = apiExplorer.SearchAnimalList("","","",kind,"","","","","",sc.getPage().toString(),"8");
        int totalCnt = Integer.parseInt(abandoned_animal[0].getTotalCount());
        PageHandler1 ph = new PageHandler1(totalCnt , sc);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("ph",ph);
        m.addAttribute("AAArr",abandoned_animal);

        return "api/AnimalList";
    }

    @GetMapping("/ShelterList")
    public String ShelterList(SearchCondition1 sc, Model m) throws IOException, ParseException {

        Shelter shelterList[] = apiExplorer.SearchShelterList("","","10", sc.getPage().toString());
        int totalCnt = Integer.parseInt(shelterList[0].getTotalCount());
        PageHandler1 ph = new PageHandler1(totalCnt , sc);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("ph",ph);
        m.addAttribute("ShelterList", shelterList);
        return "api/ShelterList";
    }

    @GetMapping("/AnimalBoard")
    public String AnimalBoard(Abandoned_animal aban_ani , Model m){
        System.out.println(aban_ani);
        m.addAttribute("aban_ani",aban_ani);
        return "api/AnimalBoard";
    }

    @GetMapping("/ShelterBoard")
    public String ShelterBoard(Shelter shelter , Model m){
        m.addAttribute("shelter",  shelter);
        return "api/ShelterBoard";
    }
    @GetMapping("/MBTI")
    public String MBTIMain(Model m) throws IOException, ParseException {
        m.addAttribute("AAArr",apiExplorer.SearchAnimalList("","","","","","","","","","1","4"));
        return "mbti/MBTIMain";
    }
    @GetMapping("/MBTITest")
    public String MBTITest(Model m){
        return "mbti/MBTITest";
    }
    @GetMapping("/MBTIResult")
    public String MBTIResult(Model m){
        return "mbti/MBTIResult";
    }

}
