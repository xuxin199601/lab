package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.Activity;
import com.csu.lab.pojo.Laboratory;
import com.csu.lab.pojo.Message;
import com.csu.lab.service.AccountService;
import com.csu.lab.service.ActivityService;
import com.csu.lab.service.LaboratoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientHomeController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private ActivityService activityService;

    //跳转到index界面
    @RequestMapping("/index")
    public String doIndex(Model model){
       /* List<Laboratory> laboratoryList = laboratoryService.getLaboratoryList();
        List<Activity> activityList = activityService.getActivityList();

        //分页处理activity里的信息放到首页（应该做成逆序查询，待改）
        PageInfo pageInfo = new PageInfo(activityList,5);
        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("laboratory" ,laboratoryList.get(0));
        model.addAttribute("activity" ,activityList.get(0));*/

        return "client/index";
    }

    @RequestMapping("/home")
    public String doHome(Model model){
        List<Laboratory> laboratoryList = laboratoryService.getLaboratoryList();
        List<Activity> activityList = activityService.getActivityList();

        //分页处理activity里的信息放到首页（应该做成逆序查询，待改）
        PageInfo pageInfo = new PageInfo(activityList,5);
        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("laboratory" ,laboratoryList.get(0));
        model.addAttribute("activity" ,activityList.get(0));
        return "client/home";
    }


}
