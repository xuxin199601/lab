package com.csu.lab.controller;

import com.csu.lab.pojo.Direction;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.DirectionService;
import com.csu.lab.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/server")
public class TutorsController {

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private DirectionService directionService;

    // 获取所有研究员的信息
    @GetMapping("/allTutorList")
    public Message getResearcherAll() {
        List<Researcher> researcherList = researcherService.getResearcherList();
        return Message.success().add(researcherList);
    }

    // 分页获取导师信息列表
//    @GetMapping("/tutorList")
//    public Message getTutorByPage(@RequestParam("person_type")Integer personType,
//                                       @RequestParam("page_index")Integer pageIndex,
//                                       @RequestParam("page_size")Integer pageSize) {
//        List<Researcher> researcherList = researcherService.queryResearcherListPaged(personType, pageIndex, pageSize);
//        return Message.success().add(researcherList);
//    }

    // 添加导师
    @PostMapping("/addTutor")
    public Message addTutor(@ModelAttribute Researcher researcher) throws Exception {
        researcherService.saveResearcher(researcher);
        return Message.success().add("添加成功");
    }

    // 修改导师信息
    @PutMapping("/modifyTutor")
    public Message modifyTutor(@ModelAttribute Researcher researcher){
        researcherService.updateResearcher(researcher);
        return Message.success().add("Success");
    }

    // 删除导师信息
    @DeleteMapping("/deleteTutor")
    public Message deleteTutor(@RequestParam("rid")Integer rid) {
        researcherService.deleteResearcher(rid);
        return Message.success().add("Success");
    }


//    /**
//     * 查询所有员工返回列表页面
//     */
//    @GetMapping(value = "/emps")
//    public String list(Model model){
//
//        Collection<Employee> employees = employeeDao.getAll();
//        model.addAttribute("emps",employees);
//        return "emp/list";
//    }

    /**
     * 跳转到编辑导师界面
     */
    @GetMapping("/tutor/{rid}")
    public String getTutorById(@PathVariable("rid")Integer rid,
                               ModelMap modelMap) {
        Researcher tutor = researcherService.queryResearcherById(rid);
        modelMap.addAttribute("tutor", tutor);
        return "tutor_add";
    }

    /**
     * 修改按钮，更新导师信息，返回至list
     */
    @PutMapping("/saveTutor")
    public String saveteTutor(@ModelAttribute Researcher researcher,
                              ModelMap modelMap) {
        researcherService.updateResearcher(researcher);
        return "redirect:/server/tutorList";
    }

    /**
     * 添加按钮，添加导师信息，返回至list
     */
    @PostMapping("/saveTutor")
    public String saveTutor(@ModelAttribute Researcher researcher,
                            ModelMap modelMap) throws Exception {
        researcherService.saveResearcher(researcher);
        return "redirect:/server/tutorList";
    }

    /**
     * 跳转到添加导师页面
     */
    @GetMapping(value = "/addTutor")
    public String toAddPage(ModelMap model){
        List<Direction> directionList = directionService.getDirectionList();
        model.addAttribute("directionList",directionList);
        return "tutor_add";
    }

    /**
     * 删除导师信息
     */
    @DeleteMapping("/tutor/{rid}")
    public String delTutor(@PathVariable("rid")Integer rid,
                           ModelMap modelMap) {
        researcherService.deleteResearcher(rid);
        return "redirect:/server/tutorList";
    }

}
