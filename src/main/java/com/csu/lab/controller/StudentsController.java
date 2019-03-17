package com.csu.lab.controller;

import com.csu.lab.pojo.Direction;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/server")
public class StudentsController {

    @Autowired
    private ResearcherService researcherService;

    // 分页获取研究生信息列表
//    @GetMapping("/studentList")
//    public Message getStudentByPage(@RequestParam("person_type")Integer personType,
//                                       @RequestParam("page_index")Integer pageIndex,
//                                       @RequestParam("page_size")Integer pageSize) {
//        List<Researcher> researcherList = researcherService.queryResearcherListPaged(personType, pageIndex, pageSize);
//        return Message.success().add(researcherList);
//    }

    // 通过id获取研究生个人信息
    @GetMapping("/studentInfo")
    public Message getStudentById(@RequestParam("rid")Integer rid) {
        Researcher Student = researcherService.queryResearcherById(rid);
        return Message.success().add(Student);
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

    /**
     * 跳转到编辑研究生界面
     */
    @GetMapping("/student/{rid}")
    public String getStudentById(@PathVariable("rid")Integer rid,
                               ModelMap modelMap) {
        Researcher student = researcherService.queryResearcherById(rid);
        modelMap.addAttribute("student", student);
        return "student_add";
    }

    /**
     * 修改按钮，更新研究生信息，返回至list
     */
    @PutMapping("/saveStudent")
    public String saveteStudent(@ModelAttribute Researcher researcher) {
        researcherService.updateResearcher(researcher);
        return "redirect:/server/studentList";
    }

    /**
     * 添加按钮，添加研究生信息，返回至list
     */
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Researcher researcher) throws Exception {
        researcherService.saveResearcher(researcher);
        return "redirect:/server/studentList";
    }

    /**
     * 跳转到添加研究生页面
     */
    @GetMapping(value = "/addStudent")
    public String toAddPage(ModelMap model){
        return "student_add";
    }

    /**
     * 删除研究生信息
     */
    @DeleteMapping("/student/{rid}")
    public String delStudent(@PathVariable("rid")Integer rid) {
        researcherService.deleteResearcher(rid);
        return "redirect:/server/studentList";
    }

}
