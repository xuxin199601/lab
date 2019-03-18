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
@RequestMapping("/server/tutor")
public class TutorsController {

    @Autowired
    private ResearcherService researcherService;

    // 分页获取导师信息列表
    @GetMapping("/tutorList")
    public String getTutorByPage(@RequestParam("person_type")Integer personType,
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

        return "server/user/tutorManage";
    }

    // 通过id获取导师个人信息
    @GetMapping("/tutorInfo")
    public Message getTutorById(@RequestParam("rid")Integer rid) {
        Researcher tutor = researcherService.queryResearcherById(rid);
        return Message.success().add(tutor);
    }

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

    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */

}
