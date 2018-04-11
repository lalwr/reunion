package com.reunion.controller;

import com.reunion.dao.MemberSchoolDao;
import com.reunion.dao.ReunionDao;
import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;
import com.reunion.domain.School;
import com.reunion.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    SchoolDao schoolDao;
    @Autowired
    ReunionDao memberDao;
    @Autowired
    MemberSchoolDao memberSchoolDao;

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
    @PostMapping(value = "/signUp")
    public String signUp(@RequestParam(name = "id") String id, @RequestParam(name = "name") String name,
                         @RequestParam(name = "password") String password, @RequestParam(name = "school") String school){
        Date date = new Date();
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setName(name);
        member.setRegDate("2018-04-11");
        member.setEditDate("2018-04-11");
        int memberNo = memberDao.insertReunion(member);
        int schoolNo = schoolDao.selectSchool(school).getNo();
        MemberSchool ms = new MemberSchool();
        ms.setMemberNo(memberNo);
        ms.setSchoolNo(schoolNo);
        memberSchoolDao.insert(ms);


        return "login";
    }
}