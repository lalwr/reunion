package com.reunion.controller;

import com.reunion.domain.Member;
import com.reunion.domain.School;
import com.reunion.security.ShaEncoding;
import com.reunion.service.MemberSchoolService;
import com.reunion.service.MemberService;
import com.reunion.service.SchoolService;
import com.reunion.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String login(@RequestParam(name = "referer", required = false)String referer, ModelMap modelMap){
        modelMap.addAttribute("referer",referer);
        return "/memberManaging/login";
        }

    @PostMapping(value = "/loginCheck")
    public String loginCheck(HttpSession session,
                             @RequestParam(name = "referer") String referer,
                             @RequestParam(name = "id") String id,
                             @RequestParam(name = "password") String password,
                             ModelMap modelMap){
        Member member = memberService.getMember(id);
        password = ShaEncoding.cryptedPwd(password);
        System.out.println("password : " + password);
        System.out.println("memberPassword : " + member.getPassword());
        if(member != null){
            if(member.getPassword().equals(password)){ // 로그인 성공
                session.setAttribute("loginId", member.getId());
                if(referer == null || "".equals(referer)){
                    return "redirect:/reunion/list";
                }else{
                    return "redirect:" + referer;
                }

            }else{ // 비밀번호 불일치
                StringBuffer js = new StringBuffer();
                js.append("<script>alert('비밀번호가 일치하지 않습니다.')</script>");
                modelMap.addAttribute("js",js.toString());
                return "/memberManaging/login";
            }
        }
        // 아이디 존재하지 않음.
        StringBuffer js = new StringBuffer();
        js.append("<script>alert('아이디가 존재하지 않습니다.')</script>");
        modelMap.addAttribute("js",js.toString());
        return "/memberManaging/login";

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

        password = ShaEncoding.cryptedPwd(password);
        signUpService.signUp(id,name,password,school);

        return "redirect:/member/login";
    }

    @GetMapping(value = "/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("loginId");
        return "redirect:/member/login";
    }
}