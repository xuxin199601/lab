package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.*;
import com.csu.lab.service.DirectionService;
import com.csu.lab.service.ResearcherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.RelationService;
import java.util.*;

@Controller
@RequestMapping("/client/tutor")
public class ClientTutorsController {

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private DirectionService directionService;

    @RequestMapping("/tutorList")
    public String getClientTutorList(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "12") Integer pageSize,
                                  Model model) {

        PageHelper.startPage(page,pageSize);

        List<Researcher> tutorList = researcherService.getResearcherList(0);

        PageInfo pageInfo = new PageInfo(tutorList,12);

        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //获得总页数
        model.addAttribute("totalPages",pageInfo.getPages());

        return "client/tutor/index";
    }

    @RequestMapping("/tutor")
    public String getClientTutor(@RequestParam(name = "id",required = false) int rid,
                                 Model model){
        //根据ID获取导师信息
        Researcher tutor = researcherService.queryResearcherById(rid);

        /*String directionStr=tutor.getDirection();

        String[] StrArray=Str.split(",");

        for(int i=0;i<StrArray.length;i++){

            System.out.println(StrArray[i]);

        }

        List<Direction> directionList = directionService.queryDirectionById(tutor.getDirection());*/

        //根据ID获取论文、项目关系
        //Relationship relationship = relationService;

        //获取导师名下的学生
        List<Researcher> studentList = researcherService.queryByProperty("affiliatedTutor",rid);

        model.addAttribute("tutor",tutor);
        model.addAttribute("studentList" , studentList);


        return "client/tutor/tutor";
    }


}
