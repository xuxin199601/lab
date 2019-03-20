package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by G on 2019/3/19.
 */
@Controller
@RequestMapping("/client/student")
public class ClientStudentsController {

    @Autowired
    private ResearcherService researcherService;

    @RequestMapping("/studentList")
    public String getClientStudentList(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "12") Integer pageSize,
                                        Model model) {

        PageHelper.startPage(page,pageSize);

        List<Researcher> studentList = researcherService.queryAllStudent();

        PageInfo pageInfo = new PageInfo(studentList,12);

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
        //获得总共查询到多少条
        model.addAttribute("totalNum",studentList.size());

        return "client/student/index";
    }

    @RequestMapping("/student")
    public String getClientTutor(@RequestParam(name = "id",required = false) int rid,
                                 Model model){
        //根据ID获取学生信息
        Researcher student = researcherService.queryResearcherById(rid);

        //获取学生的导师信息
        List<Researcher> tutorList = researcherService.queryByProperty("rid",student.getAffiliatedTutor());

        model.addAttribute("student",student);
        model.addAttribute("tutorList" , tutorList);


        return "client/student/student";
    }
}
