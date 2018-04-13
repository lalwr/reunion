package com.reunion.controller;

import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;
import com.reunion.domain.School;
import com.reunion.service.MemberSchoolService;
import com.reunion.service.MemberService;
import com.reunion.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping (value = "/member", produces="text/html;charset=UTF-8")

public class LoginController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberSchoolService memberSchoolService;

    @GetMapping(value = "/login")
    public String login(){
        return "/memberManaging/login";
    }

    @ResponseBody
    @GetMapping(value = "/idCheck")
    public String idCheck(@RequestParam(name = "id") String id,ModelMap modelMap){
        boolean idCheck = (memberService.getMember(id) == null);
        modelMap.addAttribute("idCheck",idCheck);
        if(idCheck){
            return "사용 가능한 아이디 입니다.";
        }else{
            return "이미 등록된 아이디 입니다.";
        }
    }

    @GetMapping(value = "/join")
    public String join(ModelMap modelMap){
        List<School> schools = schoolService.getSchools();
        modelMap.addAttribute("schools", schools);

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
        int memberNo = memberService.addMember(member);
        int schoolNo = schoolService.getSchool(school).getNo();
        MemberSchool ms = new MemberSchool();
        ms.setMemberNo(memberNo);
        ms.setSchoolNo(schoolNo);
        memberSchoolService.addMemberSchool(ms);


        return "redirect:/login";
    }
}