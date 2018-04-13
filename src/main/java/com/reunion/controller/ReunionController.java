package com.reunion.controller;

import com.reunion.common.ReunionPagingInfo;
import com.reunion.domain.Condition;
import com.reunion.domain.Reunion;
import com.reunion.service.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String RegisterReunion(Reunion reunion, ModelMap modelMap) throws Exception {
        reunion.setRegId("kim");
        reunionService.writeReunion(reunion);

        return "redirect:/reunion/list";
    }

    @GetMapping(value = "/view/{reunionNo}")
    public String viewReunion(@ModelAttribute("condition") Condition condition, @PathVariable String reunionNo, ModelMap modelMap) throws Exception {

        modelMap.addAttribute("result", reunionService.detailReunion(reunionNo));

        return "reunion/reunionView";
    }

    @GetMapping(value = "/detail/{reunionNo}")
    public String detailReunion(@ModelAttribute("condition") Condition condition, @PathVariable String reunionNo, ModelMap modelMap) throws Exception {

        modelMap.addAttribute("result", reunionService.detailReunion(reunionNo));

        return "reunion/reunionDetail";
    }

    @PutMapping(value = "/update")
    public String updateReunion(Reunion reunion, ModelMap modelMap) throws Exception {
        reunionService.updateReunion(reunion);
        return "redirect:/reunion/list";
    }

    @DeleteMapping(value = "/delete")
    public String deleteReunion(Reunion reunion, ModelMap modelMap) throws Exception {
        reunionService.deleteReunion(reunion);
        return "redirect:/reunion/list";
    }
}
