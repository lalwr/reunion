package com.reunion.controller;

import com.reunion.domain.Reunion;
import com.reunion.service.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/reunion")
public class ReunionController {

    @Autowired
    ReunionService reunionService;

    @GetMapping(value = "/list")
    public String reunion(Reunion reunion, ModelMap modelMap) throws Exception{
        List<Reunion> reunionList = reunionService.list(reunion);

        modelMap.addAttribute("reunionList", reunionList);
        return "reunion/reunionList";
    }

    @GetMapping(value = "/content/{reunionNo}")
    public String reunionContent(Reunion reunion, @PathVariable int reunionNo, ModelMap modelMap) throws Exception{
        reunion.setNo(reunionNo);
        modelMap.addAttribute("reunionContent", reunionService.content(reunion));
        return "reunion/reunionView";
    }
}
