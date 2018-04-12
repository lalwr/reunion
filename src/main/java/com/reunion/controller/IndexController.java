package com.reunion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @GetMapping(value ="/")
    public String indexRedirect(){
        return "redirect:/reunion/list";
    }

}
