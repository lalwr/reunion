package com.reunion.controller;

import com.reunion.domain.*;
import com.reunion.security.ShaEncoding;
import com.reunion.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;


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
    @Autowired
    InfoService infoService;

    @GetMapping(value = "/login")
    public String login(HttpSession session, @RequestParam(name = "referer", required = false)String referer, ModelMap modelMap){
        modelMap.addAttribute("referer",referer);
        if(session.getAttribute("loginId")== null || "".equals(session.getAttribute("loginId"))){
            return "/memberManaging/login";
        }else{
            return "redirect:/reunion/list";
        }
    }

    @DeleteMapping(value = "/delete")
    public String delete(HttpSession session){
        signUpService.delete((String)session.getAttribute("loginId"));
        session.removeAttribute("loginId");
        return "redirect:/member/login";
    }

    @GetMapping(value = "/updateForm")
    public String updateForm(HttpSession session, ModelMap modelMap){
        List<School> schools = schoolService.getSchools();
        modelMap.addAttribute("schools", schools);
        String memberId = (String) session.getAttribute("loginId");
        Info info = infoService.showInfo(memberId);
        modelMap.addAttribute("info",info);
        return "/memberManaging/updateForm";
    }

    @PutMapping(value = "/update")
    public String update(HttpSession session,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name ="school") String school){
        String memberId = (String)session.getAttribute("loginId");
        signUpService.update(memberId,password,school);

        return "redirect:/member/showInfo";
    }

    @GetMapping(value = "/showInfo")
    public String showInfo(HttpSession session, ModelMap modelMap){
        String memberId = (String) session.getAttribute("loginId");
        Member member = memberService.getMember(memberId);
        Info info = infoService.showInfo(memberId);
        ProfPicDTO profile = signUpService.getProfile(member.getNo());
        modelMap.addAttribute("info",info);
        modelMap.addAttribute("profile",profile);

        return "/memberManaging/showInfo";
    }

    @GetMapping("/image/{memberNo}")
    @ResponseBody
    public void fileImgview(@PathVariable(name = "memberNo")int memberNo,
                            HttpServletResponse response) throws Exception{

        ProfPicDTO profile = signUpService.getProfile(memberNo);
        System.out.println(profile.getFileSize());
        System.out.println(profile.getName());
        System.out.println(profile.getFormat());

        response.setHeader("Content-Type", profile.getFormat());
        response.setHeader("Content-Length", "" + profile.getFileSize());
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        // try - with - resources 자동으로 close 해준다.
        try(
                OutputStream out = response.getOutputStream();
                FileInputStream fis = new FileInputStream(profile.getPath() + profile.getTempName());
        ){
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file view Error");
        }
    }

    @PostMapping(value = "/loginCheck")
    public String loginCheck(HttpSession session,
                             @RequestParam(name = "referer") String referer,
                             @RequestParam(name = "id") String id,
                             @RequestParam(name = "password") String password,
                             ModelMap modelMap){
        Member member = memberService.getMember(id);
        password = ShaEncoding.cryptedPwd(password);
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
    public String signUp(@RequestParam(name = "id") String id,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "school") String school,
                         @RequestParam("uploadFile") MultipartFile file){

        password = ShaEncoding.cryptedPwd(password);
        signUpService.signUp(id,name,password,school);
        signUpService.profile(id, file);

        return "redirect:/member/login";
    }

    @GetMapping(value = "/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("loginId");
        return "redirect:/member/login";
    }
}