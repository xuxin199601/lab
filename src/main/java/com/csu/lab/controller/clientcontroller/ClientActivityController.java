package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.Activity;
import com.csu.lab.pojo.Message;
import com.csu.lab.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by G on 2019/3/20.
 */
@Controller
@RequestMapping("/client/activity")
public class ClientActivityController {

    @Autowired
    private ActivityService activityService;

    // 分页活动信息列表
    @RequestMapping("/activityList")
    public String getClientActivityList(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  Model model) {
        PageHelper.startPage(pageNum,pageSize);

        List<Activity> activityList = activityService.getActivityList();

        PageInfo pageInfo = new PageInfo(activityList,10);

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

        model.addAttribute("activityList",activityList);

        return "client/activity/index";
    }

    // 通过id获取活动信息
    @RequestMapping("/activity")
    public String getClientActivity(@RequestParam(name="id", required = false)Integer aid,
                                   Model model) {

        List<Activity> activityList = activityService.queryByProperty("aid",aid);

        model.addAttribute("activity",activityList.get(0));

        return "client/activity/activity";
    }
}
