package com.csu.lab.controller.clientcontroller;

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

    // 分页获取研究生信息列表
    @GetMapping("/studentList")
    public String getStudentByPage(@RequestParam("person_type")Integer personType,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    Model model) {

        PageHelper.startPage(pageNum,pageSize);

        List<Researcher> researcherList = researcherService.getResearcherList(personType);

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

    // 通过id获取研究生个人信息
    @GetMapping("/studentInfo")
    public Message getStudentById(@RequestParam("rid")Integer rid) {
        Researcher tutor = researcherService.queryResearcherById(rid);
        return Message.success().add(tutor);
    }

    // 添加研究生
    @PostMapping("/addStudent")
    public Message addStudent(@ModelAttribute Researcher researcher) throws Exception {
        researcherService.saveResearcher(researcher);
        return Message.success().add("添加成功");
    }

    // 修改研究生信息
    @PutMapping("/modifyStudent")
    public Message modifyStudent(@ModelAttribute Researcher researcher) {
        researcherService.updateResearcher(researcher);
        return Message.success().add("Success");
    }

    // 删除研究生信息
    @DeleteMapping("/deleteStudent")
    public Message deleteStudent(@RequestParam("rid")Integer rid) {
        researcherService.deleteResearcher(rid);
        return Message.success().add("Success");
    }

}
