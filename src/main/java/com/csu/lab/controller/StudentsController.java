package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/server/student")
public class StudentsController {

    @Autowired
    private ResearcherService researcherService;

    // 跳转到导师管理界面
    @RequestMapping("/studentList")
    public String studentList(@RequestParam(name = "person_type", defaultValue = "1")Integer personType,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "value", required = false) String value,
                            Model model) {

        PageHelper.startPage(pageNum,pageSize);

        List<Researcher> researcherList;
        if (value != null){
            model.addAttribute("key", value);
            researcherList = researcherService.queryStudentByProperty("name", value);
        }else {
            researcherList = researcherService.getResearcherList(personType);
        }

        // 查询导师信息
        List<Researcher> tutors = researcherService.getResearcherList(0);
        model.addAttribute("tutors", tutors);

        PageInfo pageInfo = new PageInfo(researcherList,10);

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
        return "server/user/studentManage";
    }

    // 跳转到新增导师页面
    @RequestMapping("/addStudent")
    public String toAddPage() {
        return "server/user/addStudent";
    }

    // 跳转到修改导师页面
    @RequestMapping("/editStudent")
    public String toEditPage(@RequestParam("id")Integer aid,
                             Model model) {
        Researcher researcher = researcherService.queryResearcherById(aid);
        model.addAttribute("researcher", researcher);
        return "server/user/addStudent";
    }

    // 保存添加的导师信息，跳转到导师管理界面
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(Researcher researcher) throws Exception {
        researcherService.saveResearcher(researcher);
        return "redirect:/server/student/studentList";
    }

    // 保存修改的导师信息，跳转到导师管理界面
    @RequestMapping(value = "/saveStudent", method = RequestMethod.PUT)
    public String saveEditStudent(Researcher researcher) {
        researcherService.updateResearcher(researcher);
        return "redirect:/server/student/studentList";
    }

    // 删除导师信息，跳转到导师管理界面
    @RequestMapping("/deleteStudent")
    public String delStudent(@RequestParam("id")Integer rid) {
        researcherService.deleteResearcher(rid);
        return "redirect:/server/student/studentList";
    }
}
