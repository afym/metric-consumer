package com.afym.controller;

import com.afym.connector.ElasticsearchConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private ElasticsearchConnector elasticsearchConnector;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
}
