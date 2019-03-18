package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Activity;
import com.csu.lab.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动管理
 */
@Controller
@RequestMapping("/server/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // 分页活动信息列表
    @RequestMapping("/activityList")
    public String getActivityList(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    Model model) {
        PageHelper.startPage(pageNum,pageSize);

        List<Activity> list = activityService.getActivityList();

        PageInfo pageInfo = new PageInfo(list,10);

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

        return "server/activity/activityManage";
    }

    // 通过id获取活动信息
    @GetMapping("/activityInfo")
    public Message getActivityById(@RequestParam("aid")Integer pid) {
        Activity activity = activityService.queryActivityById(pid);
        return Message.success().add(activity);
    }

    // 添加活动
    @PostMapping("/addActivity")
    public Message addActivity(Activity activity) throws Exception {
        activityService.saveActivity(activity);
        return Message.success().add("添加成功");
    }

    // 修改活动信息
    @PutMapping("/modifyActivity")
    public Message modifyActivity(Activity activity) {
        activityService.updateActivity(activity);
        return Message.success().add("Success");
    }

    // 删除活动信息
    @DeleteMapping("/deleteActivity")
    public Message deleteActivity(@RequestParam("pid")Integer rid) {
        activityService.deleteActivity(rid);
        return Message.success().add("Success");
    }
}
