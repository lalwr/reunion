package com.reunion.controller;

import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;
import com.reunion.domain.School;
import com.reunion.service.MemberSchoolService;
import com.reunion.service.MemberService;
import com.reunion.service.SchoolService;
import com.reunion.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    SignUpService signUpService;

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request){
        if(request.getAttribute("loginId")==null){
            return "/memberManaging/login";
        }else{
            return "redirect:/reunion/list";
        }

    }
    @ResponseBody
    @PostMapping(value = "/loginCheck")
    public String loginCheck(HttpServletRequest request, @RequestParam(name = "id") String id, @RequestParam(name = "password") String password){
        Member member = memberService.getMember(id);
        if(member != null){
            if(member.getPassword().equals(password)){ // 로그인 성공
                HttpSession session = request.getSession();
                session.setAttribute("loginId", member.getId());
                return "success";
            }else{ // 비밀번호 불일치
                return "incorrectPw";
            }
        }
        // 아이디 존재하지 않음.
        return "noId";

    }

    @ResponseBody
    @GetMapping(value = "/idCheck")
    public String idCheck(@RequestParam(name = "id") String id){
        if(memberService.getMember(id) == null)
            return "true"; //true
        else
            return "false"; // false
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

        signUpService.signUp(id,name,password,school);

        return "redirect:/member/login";
    }

    @GetMapping(value = "/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession(false).invalidate();
        return "redirect:/member/login";
    }
}