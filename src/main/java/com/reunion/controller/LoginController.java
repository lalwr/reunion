package com.reunion.controller;

import com.reunion.dao.MemberDao;
import com.reunion.dao.MemberSchoolDao;
import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;
import com.reunion.domain.School;
import com.reunion.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    SchoolDao schoolDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    MemberSchoolDao memberSchoolDao;

    @GetMapping(value = "/login")
    public String login(){
        return "/memberManaging/login";
    }

    @GetMapping(value = "/join")
    public String join(ModelMap modelMap){
        List<School> schools = schoolDao.selectAll();
        modelMap.addAttribute("schools", schools);

        List<Member> members = memberDao.selectAll();
        modelMap.addAttribute("members",members);

        return "/memberManaging/join";
    }
    @PostMapping(value = "/signUp")
    public String signUp(@RequestParam(name = "id") String id, @RequestParam(name = "name") String name,
                         @RequestParam(name = "password") String password, @RequestParam(name = "school") String school){
        Date date = new Date();
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setName(name);
        member.setRegDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        member.setEditDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        int memberNo = memberDao.insertMember(member);
        int schoolNo = schoolDao.selectSchool(school).getNo();
        MemberSchool ms = new MemberSchool();
        ms.setMemberNo(memberNo);
        ms.setSchoolNo(schoolNo);
        memberSchoolDao.insertMemberSchool(ms);


        return "redirect:/login";
    }
}