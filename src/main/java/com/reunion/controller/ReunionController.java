package com.reunion.controller;

import com.reunion.common.ReunionPagingInfo;
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
    public String listReunion(@ModelAttribute("reunion") Reunion reunion, ModelMap modelMap) throws Exception {
        List<Reunion> reunionList = reunionService.listReunion(reunion);
        int totCount = reunionService.listCnt(reunion);
        reunion.setTotCount(totCount);
        modelMap.addAttribute("reunionList", reunionList);

        ReunionPagingInfo pagingInfo = new ReunionPagingInfo();
        pagingInfo.setPage(reunion.getPage());
        pagingInfo.setTotCount(reunion.getTotCount());
        modelMap.addAttribute("pagingInfo",pagingInfo);

        return "reunion/reunionList";
    }

    @PostMapping(value = "/write")
    public String writeReunion(Reunion reunion, ModelMap modelMap) throws Exception {
        reunion.setRegId("kim");
        reunionService.writeReunion(reunion);

        return "redirect:/reunion/list";
    }

    @GetMapping(value = "/detail/{reunionNo}")
    public String detailReunion(Reunion reunion, @PathVariable String reunionNo, ModelMap modelMap) throws Exception {

        if(reunionNo.equals("new")){
            Reunion emptyReunion =  new Reunion();
            emptyReunion.setCategoryNo("1"); //임시 설정
            emptyReunion.setSchoolNo("1"); //임시 설정
            modelMap.addAttribute("result", emptyReunion);
            return "reunion/reunionView";
        }else{
            modelMap.addAttribute("result", reunionService.detailReunion(reunionNo));
        }

        return "reunion/reunionView";
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
