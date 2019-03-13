package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Activity;
import com.csu.lab.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // 分页活动信息列表
    @GetMapping("/activityList")
    public Message getStudentByPage(@RequestParam("page_index")Integer pageIndex,
                                    @RequestParam("page_size")Integer pageSize) {
        List<Activity> activityList = activityService.queryActivityListPaged(pageIndex, pageSize);
        return Message.success().add(activityList);
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
