package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.*;
import com.csu.lab.service.AccountService;
import com.csu.lab.service.ActivityService;
import com.csu.lab.service.LaboratoryService;
import com.csu.lab.service.ResearcherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientHomeController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ResearcherService  researcherService;

    //跳转到index界面
    @RequestMapping("/index")
    public String doIndex(Model model ,HttpSession session){

        /*System.out.println("session是："+ session.getAttribute("user"));
        boolean sessionState = false;
        //如果获取到了session，则把登录成功的状态返回给前端
        if (session.getAttribute("user")!=null) {
            sessionState = true;

            System.out.println("sessionState是：" + sessionState);
            Account account = (Account) session.getAttribute("user");
            System.out.println("account是：" + account);

            //通过aid查询用户，获得个人信息
            List<Researcher> researcherList = researcherService.queryByProperty("aid", account.getAid());
            model.addAttribute("researcher", researcherList.get(0));
        }
        model.addAttribute("sessionState", sessionState);
        System.out.println("model里有"+model);*/

        boolean sessionState = true;
        System.out.println(session.getAttribute("user"));
        if (session.getAttribute("user")!=null)
            sessionState = false;

        model.addAttribute("sessionState", sessionState);

        return "client/index";
    }

    @RequestMapping("/home")
    public String doHome(Model model ){
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
