package com.csu.lab.controller;

import com.csu.lab.customConst.CustomConstant;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Activity;
import com.csu.lab.pojo.Project;
import com.csu.lab.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 活动管理
 */
@Controller
@RequestMapping("/server/activity")
public class ActivityController {


    String msg;
    @Autowired
    private ActivityService activityService;

    // 分页活动信息列表
    @RequestMapping("/activityList")
    public String getActivityList(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "value", required = false) String value,
                                  Model model, HttpSession session) {

        session.setAttribute("error", msg);
        msg = null;

        PageHelper.startPage(pageNum, pageSize);


        List<Activity> list;
        if (value != null && !value.equals("")) {
            model.addAttribute("key", value);
            list = activityService.queryByProperty("name", value);
        } else {
            list = activityService.getActivityList();
        }


//        List<Activity> list = activityService.getActivityList();

        PageInfo pageInfo = new PageInfo(list, 10);

        model.addAttribute("pageInfo", pageInfo);

        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());

        return "server/activity/activityManage";
    }

    /**
     * 跳转代码
     */

    // 添加活动
    @RequestMapping("/addActivity")
    public String addActivity() {
        return "server/activity/addActivity";
    }

    // 修改活动信息
    @RequestMapping("/editActivity")
    public String modifyActivity(@RequestParam("id") Integer pid,
                                 Model model) {
//        activityService.updateActivity(activity);
        Activity activity = activityService.queryActivityById(pid);
        model.addAttribute("activity", activity);
        return "server/activity/addActivity";
    }

    /**
     * 逻辑代码啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
     *
     * @param
     * @return
     */

//    // 通过id获取活动信息
//    @RequestMapping("/activityInfo")
//    public String getActivityById(@RequestParam("aid") Integer pid) {
//        Activity activity = activityService.queryActivityById(pid);
////        return Message.success().add(activity);
//    }


    // 添加活动
    @RequestMapping(value = "/saveActivity", method = RequestMethod.POST)
    public String addActivity(Activity activity) throws Exception {
        int result = activityService.saveActivity(activity);
        if (result == 1) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return "redirect:/server/activity/activityList";
    }

    @RequestMapping(value = "/saveActivity", method = RequestMethod.PUT)
    public String editActivity(Activity activity) {

        int result = activityService.updateActivity(activity);
        if (result == CustomConstant.STATUS_SUCCESS) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return "redirect:/server/activity/activityList";
    }


    // 删除活动信息
    @RequestMapping("/deleteActivity")
    public String deleteActivity(@RequestParam("id") Integer rid) {
        int result = activityService.deleteActivity(rid);
        if (result == 1) {
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }
        return "redirect:/server/activity/activityList";
    }
}
