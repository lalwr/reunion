package com.reunion.controller;

import com.reunion.domain.School;
import com.reunion.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    SchoolDao schoolDao;

    @GetMapping(value = "/login")
    public String reunionLogin(){
        return "login";
    }
    @PostMapping(value = "/login")
    public String reunionPostLogin(){
        return "login";
    }

    @GetMapping(value = "/join")
    public String reunionJoin(ModelMap modelMap){
        List<School> schools = schoolDao.selectAll();
        modelMap.addAttribute("schools", schools);
        return "join";
    }
}