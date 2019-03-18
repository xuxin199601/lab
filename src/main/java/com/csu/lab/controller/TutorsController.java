package com.csu.lab.controller;

import com.csu.lab.pojo.Account;
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

    // 跳转到导师管理界面
    @RequestMapping("/tutorList")
    public String tutorList(@RequestParam(name = "person_type", defaultValue = "0")Integer personType,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "value", required = false) String value,
                              Model model) {

        PageHelper.startPage(pageNum,pageSize);

        List<Researcher> researcherList;
        if (value != null){
            model.addAttribute("key", value);
            researcherList = researcherService.queryTutorByProperty("name", value);
        }else {
            researcherList = researcherService.getResearcherList(personType);
        }

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

    // 跳转到新增导师页面
    @RequestMapping("/addTutor")
    public String toAddPage() {
        return "server/user/addTutor";
    }

    // 跳转到修改导师页面
    @RequestMapping("/editTutor")
    public String toEditPage(@RequestParam("id")Integer aid,
                             Model model) {
        Researcher researcher = researcherService.queryResearcherById(aid);
        model.addAttribute("researcher", researcher);
        return "server/user/addTutor";
    }

    // 保存添加的导师信息，跳转到导师管理界面
    @RequestMapping(value = "/saveTutor", method = RequestMethod.POST)
    public String saveTutor(Researcher researcher) throws Exception {
        researcherService.saveResearcher(researcher);
        return "redirect:/server/tutor/tutorList";
    }

    // 保存修改的导师信息，跳转到导师管理界面
        @RequestMapping(value = "/saveTutor", method = RequestMethod.PUT)
    public String saveEditTutor(Researcher researcher) {
        researcherService.updateResearcher(researcher);
        return "redirect:/server/tutor/tutorList";
    }

    // 删除导师信息，跳转到导师管理界面
    @RequestMapping("/deleteTutor")
    public String delTutor(@RequestParam("id")Integer rid) {
        researcherService.deleteResearcher(rid);
        return "redirect:/server/tutor/tutorList";
    }

    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */

}
