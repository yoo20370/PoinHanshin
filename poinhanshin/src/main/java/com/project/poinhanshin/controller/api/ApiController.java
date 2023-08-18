package com.project.poinhanshin.controller.api;

import com.project.poinhanshin.etc.ApiExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ApiController {
    ApiExplorer apiExplorer;
    
    @Autowired
    public ApiController(ApiExplorer apiExplorer) {
        this.apiExplorer = apiExplorer;
    }

}
