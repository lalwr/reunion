package com.reunion.controller;

import com.reunion.domain.Info;
import com.reunion.domain.Member;
import com.reunion.domain.School;
import com.reunion.security.ShaEncoding;
import com.reunion.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


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
        Info info = infoService.showInfo(memberId);
        modelMap.addAttribute("info",info);

        return "memberManaging/showInfo";
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

    @PostMapping(value = "/upload")
    public String picUpload(@RequestParam("uploadFile") MultipartFile file){
        System.out.println("file name : " + file.getOriginalFilename()); //실제 파일명
        System.out.println("file size : " + file.getSize()); // 파일 크기
        System.out.println("file type : " + file.getContentType()); // 파일 type

        // 중복된 파일 문제를 해결.
        // 외부에서는 직접 접근하면 안된다. ex> jsp를 업로드 실행할 수 없는 경로 (외부에서 접근하지 못하는 경로)
        // 하나의 폴더에 너무 많은 파일이 저장되면 관리하기 어렵다.
        // /tmp/helloboard 에 저장하도록 한다.
        // /tmp/helloboard/년/월/일/uuid
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // 월은 0부터 시작
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // 윈도우의 경우엔 디렉토리 구분자가 \
        // unix계열은 디렉토리 구분자가 /
        // File.separator 를 이용하여 디렉토리를 구분한다.
        StringBuffer sb = new StringBuffer("/Users/life4honor/Downloads/profPic/");
        sb.append(year);
        sb.append("/");
        sb.append(month);
        sb.append("/");
        sb.append(day);
        sb.append("/");

        String dir = sb.toString();

        File fileObj = new File(dir);
        if(!fileObj.exists()){ // 해당 디렉토리가 존재하지 않는다면
            fileObj.mkdirs(); // 하위 폴더까지 생성한다.
        }

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString();  // 파일명
        String saveFilePath = dir + saveFileName; // 디렉토리 + 파일명

        //file.getBytes() // 망하자.
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = file.getInputStream();
            out = new FileOutputStream(saveFilePath);
            byte[] buffer = new byte[1024];
            int readCount = 0;
            // 만약 파일길이가 1026
            // 1024
            // 2
            while((readCount = in.read(buffer)) != -1){ // -1(EoF)
                out.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //db 에는 다음과 같은 내용이 저장될 것이다.
        // 게시물 id, 글쓴이 id, title, 내용,
        // 파일 id, 게시물id, 파일의 오리지날이름, 파일의 실제 저장경로, 파일길이, 파일type
        return "memberManaging/join";
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
                         @RequestParam(name = "school") String school){

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