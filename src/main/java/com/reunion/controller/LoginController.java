package com.reunion.controller;

import com.reunion.dao.MemberDao;
import com.reunion.dao.MemberSchoolDao;
import com.reunion.dao.ReunionDao;
import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;
import com.reunion.domain.School;
import com.reunion.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/memberManaging")
public class LoginController {
    @Autowired
    SchoolDao schoolDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    MemberSchoolDao memberSchoolDao;

    @GetMapping(value = "/login")
    public String GetLogin(){
        return "/memberManaging/login";
    }
    @PostMapping(value = "/login")
    public String PostLogin(){
        return "/memberManaging/login";
    }

    @GetMapping(value = "/join")
    public String GetJoin(ModelMap modelMap){
        List<School> schools = schoolDao.selectAll();
        modelMap.addAttribute("schools", schools);

        List<Member> members = memberDao.selectAll();
        modelMap.addAttribute("members",members);

        return "/memberManaging/join";
    }
    @PostMapping(value = "/signUp")
    public String PostSignUp(@RequestParam(name = "id") String id, @RequestParam(name = "name") String name,
                         @RequestParam(name = "password") String password, @RequestParam(name = "school") String school){

        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setName(name);
        member.setRegDate("2018-04-11");
        member.setEditDate("2018-04-11");
        int memberNo = memberDao.insert(member);
        int schoolNo = schoolDao.select(school).getNo();
        MemberSchool ms = new MemberSchool();
        ms.setMemberNo(memberNo);
        ms.setSchoolNo(schoolNo);
        memberSchoolDao.insert(ms);


        return "redirect:/memberManaging/login";
    }
}