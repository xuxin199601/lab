package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.Activity;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/tutor")
public class ClientTutorsController {

    @Autowired
    private ResearcherService researcherService;

    @RequestMapping("/tutorList")
    public String getActivityList(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "6") Integer pageSize,
                                  Model model) {
        PageHelper.startPage(pageNum,pageSize);

        //查询导师的信息，导师personType为0
        List<Researcher> researcherList = researcherService.getResearcherList(0);

        PageInfo pageInfo = new PageInfo(researcherList,6);

        model.addAttribute("pageInfo",pageInfo);

        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages",pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage",pageInfo.isIsLastPage());

        return "client/tutor/index";
    }


}
