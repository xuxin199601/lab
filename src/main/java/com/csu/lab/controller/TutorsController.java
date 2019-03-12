package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/tutor")
public class TutorsController {

    @Autowired
    private ResearcherService researcherService;

    // 获取所有研究员的信息
    @GetMapping("/allTutorList")
    public Message getResearcherAll() {
        List<Researcher> researcherList = researcherService.getResearcherList();
        return Message.success().add(researcherList);
    }

    // 分页获取导师信息列表
    @GetMapping("/tutorList")
    public Message getTutorByPage(@RequestParam("person_type")Integer personType,
                                       @RequestParam("page_index")Integer pageIndex,
                                       @RequestParam("page_size")Integer pageSize) {
        List<Researcher> researcherList = researcherService.queryResearcherListPaged(personType, pageIndex, pageSize);
        return Message.success().add(researcherList);
    }

    // 通过id获取导师个人信息
    @GetMapping("/tutorInfo")
    public Message getTutorById(@RequestParam("rid")Integer rid) {
        Researcher tutor = researcherService.queryResearcherById(rid);
        return Message.success().add(tutor);
    }

    // 添加导师
    @PostMapping("/addTutor")
    public Message addTutor(@RequestParam("aid")Integer aid,
                           @RequestParam("name")String name,
                           @RequestParam("post")String post,
                           @RequestParam("image")String image,
                           @RequestParam("person_type")Integer person_type,
                           @RequestParam("direction")String direction ,
                           @RequestParam("introduction")String introduction) throws Exception {
        Researcher researcher = new Researcher(aid, name, post, image, person_type, direction, introduction);
        researcherService.saveResearcher(researcher);
        return Message.success().add("添加成功");
    }

    // 修改导师信息
    @PutMapping("/modifyTutor")
    public Message modifyTutor(@RequestParam("rid")Integer rid,
                            @RequestParam("aid")Integer aid,
                            @RequestParam("name")String name,
                            @RequestParam("post")String post,
                            @RequestParam("image")String image,
                            @RequestParam("person_type")Integer person_type,
                            @RequestParam("direction")String direction ,
                            @RequestParam("introduction")String introduction) throws Exception {
        Researcher researcher = new Researcher(rid, aid, name, post, image, person_type, direction, introduction);
        researcherService.updateResearcher(researcher);
        return Message.success().add("Success");
    }

    // 删除导师信息
    @DeleteMapping("/deleteTutor")
    public Message deleteTuttor(@RequestParam("rid")Integer rid) {
        researcherService.deleteResearcher(rid);
        return Message.success().add("Success");
    }

}
