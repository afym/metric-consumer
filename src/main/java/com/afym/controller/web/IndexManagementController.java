package com.afym.controller.web;

import com.afym.repository.elasticsearch.index.BuildFrequencyIndex;
import com.afym.repository.elasticsearch.query.IndexListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexManagementController {
    @Autowired
    private BuildFrequencyIndex buildFrequencyIndex;
    @Autowired
    private IndexListQuery listQuery;

    @GetMapping("/web/v1/indexes/create")
    public String create(){
        this.buildFrequencyIndex.createIndex();
        return "redirect:list";
    }

    @GetMapping("/web/v1/indexes/list")
    public String list(Model model){
        model.addAttribute("indexes", this.listQuery.getIndexes());
        return "list-indexes";
    }
}
