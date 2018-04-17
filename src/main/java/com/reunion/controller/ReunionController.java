package com.reunion.controller;

import com.reunion.common.ReunionPagingInfo;
import com.reunion.domain.Condition;
import com.reunion.domain.FileVO;
import com.reunion.domain.Reunion;
import com.reunion.service.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/reunion")
public class ReunionController {

    @Autowired
    ReunionService reunionService;

    @GetMapping(value = "/list")
    public String listReunion(@ModelAttribute("condition") Condition condition, ModelMap modelMap) throws Exception {
        List<Reunion> reunionList = reunionService.listReunion(condition);
        int totCount = reunionService.listCnt(condition);
        condition.setTotCount(totCount);
        modelMap.addAttribute("reunionList", reunionList);

        ReunionPagingInfo pagingInfo = new ReunionPagingInfo();
        pagingInfo.setPage(condition.getPage());
        pagingInfo.setTotCount(condition.getTotCount());
        modelMap.addAttribute("pagingInfo",pagingInfo);

        return "reunion/reunionList";
    }

    @GetMapping(value = "/write")
    public String writeReunion(@ModelAttribute("condition") Condition condition, ModelMap modelMap) throws Exception {

        Reunion reunion =  new Reunion();
        reunion.setCategoryNo("1"); //임시 설정
        reunion.setSchoolNo("1"); //임시 설정
        modelMap.addAttribute("reunion", reunion);

        return "reunion/reunionWrite";
    }

    @PostMapping(value = "/write")
    public String RegisterReunion(HttpSession session, @ModelAttribute("reunion") Reunion reunion, @RequestParam("file") MultipartFile[] files, ModelMap modelMap) throws Exception {

        String loginId = (String)session.getAttribute("loginId");
        reunion.setRegId(loginId);
        reunion.setRegDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        reunion.setEditDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        reunionService.writeReunion(reunion, files);

        return "redirect:/reunion/list";
    }

    @GetMapping(value = "/view/{reunionNo}")
    public String viewReunion(@ModelAttribute("condition") Condition condition, @PathVariable String reunionNo, ModelMap modelMap) throws Exception {

        modelMap.addAttribute("result", reunionService.detailReunion(reunionNo));
        modelMap.addAttribute("files", reunionService.fileReunion(reunionNo));

        return "reunion/reunionView";
    }

    @GetMapping(value = "/detail/{reunionNo}")
    public String detailReunion(@ModelAttribute("condition") Condition condition, @PathVariable String reunionNo, ModelMap modelMap) throws Exception {

        modelMap.addAttribute("result", reunionService.detailReunion(reunionNo));
        modelMap.addAttribute("files", reunionService.fileReunion(reunionNo));

        return "reunion/reunionDetail";
    }

    @PostMapping(value = "/update")
    public String updateReunion(Reunion reunion, @RequestParam("file") MultipartFile[] files,ModelMap modelMap) throws Exception {
        reunionService.updateReunion(reunion, files);
        return "redirect:/reunion/list";
    }

    @PostMapping(value = "/delete")
    public String deleteReunion(Reunion reunion, ModelMap modelMap) throws Exception {
        reunionService.deleteReunion(reunion);
        return "redirect:/reunion/list";
    }

    // Download의 경우에는 읽어들인 파일정보를 브라우저에게 출력한다.
    // 파일정보를 response를 이용하여 직접 출력하겠다.
    // @ResponseBody를 붙여주고, HttpServletResponse response를 파라미터로 주입받는다.
    @GetMapping("/download/{fileNo}")
    @ResponseBody
    public void fileDownload(@PathVariable(name = "fileNo")String fileNo,
                         HttpServletResponse response) throws Exception{

        FileVO fileVO = reunionService.fileDetail(fileNo);

        //Content-Disposition, Content-Transfer-Encoding 이 있으면 파일이 다운로드된다.
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileVO.getTempName() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", fileVO.getFormat());
        response.setHeader("Content-Length", "" + fileVO.getFileSize());
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        // try - with - resources 자동으로 close 해준다.
        try(
                FileInputStream fis = new FileInputStream(fileVO.getPath() + fileVO.getTempName());
                OutputStream out = response.getOutputStream();
        ){
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
    }

    @ResponseBody
    @GetMapping("/fileDelete/{fileNo}")
    public boolean fileDelete(@PathVariable(name = "fileNo")String fileNo) throws Exception{

        return reunionService.fileDelete(fileNo);

    }

    @GetMapping("/image/{fileNo}")
    @ResponseBody
    public void fileImgview(@PathVariable(name = "fileNo")String fileNo,
                             HttpServletResponse response) throws Exception{

        FileVO fileVO = reunionService.fileDetail(fileNo);

        response.setHeader("Content-Type", fileVO.getFormat());
        response.setHeader("Content-Length", "" + fileVO.getFileSize());
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        // try - with - resources 자동으로 close 해준다.
        try(
                OutputStream out = response.getOutputStream();
                FileInputStream fis = new FileInputStream(fileVO.getPath() + fileVO.getTempName());
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

}
