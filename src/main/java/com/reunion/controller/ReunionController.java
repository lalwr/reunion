package com.reunion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReunionController {

    @GetMapping(value = "/")
    public String reunion(){
        return "reunion/list";
    }

    @PostMapping(value = "/write")
    public String reunionWrite(){
        return "reunion/writeForm";
    }

    @GetMapping(value = "/content")
    public String reunionContent(){
        return "reunion/detail";
    }
}
