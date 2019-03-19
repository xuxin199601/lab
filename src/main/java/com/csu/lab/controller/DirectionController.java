package com.csu.lab.controller;

import com.csu.lab.pojo.Direction;
import com.csu.lab.pojo.Message;
import com.csu.lab.service.DirectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/server/direction")
public class DirectionController {

    String msg;

    @Autowired
    private DirectionService directionService;

    // 分页研究方向信息列表
    @GetMapping("/directionList")
    public String getStudentByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "value", required = false) String value,
                                   Model model,
                                   HttpSession session) {

        session.setAttribute("error", msg);
        msg = null;

        PageHelper.startPage(pageNum, pageSize);

        List<Direction> directionList;

        if (value != null){
            model.addAttribute("key", value);
            directionList = directionService.queryByProperty("resDirection", value);
        }else {
            directionList = directionService.getDirectionList();
        }
        PageInfo pageInfo = new PageInfo(directionList, 10);

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
        return "server/direction/directionManage";
    }

    //跳转到添加界面
    @RequestMapping("/doAddDirection")
    public String doAddDirection() {
        return "server/direction/addDirection";
    }

    // 添加研究方向
    @PostMapping("/saveDirection")
    public String addDirection(Direction direction) {
        int result = directionService.addDirection(direction);
        if (result == 1) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return "redirect:directionList";
    }

    // 删除研究方向信息
    @RequestMapping("/deleteDirection")
    public String deleteDirection(@RequestParam("id") Integer did) {
        int result = directionService.deleteDirection(did);
        if (result == 1) {
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }
        return "redirect:directionList";
    }

    // 通过id获取研究方向信息
    @GetMapping("/directionInfo")
    public Message getDirectionById(@RequestParam("did") Integer pid) {
        Direction direction = directionService.queryDirectionById(pid);
        return Message.success().add(direction);
    }

    //跳转到修改研究方向模块
    @RequestMapping("/doModifyDirection")
    public String doModifyDirection(@RequestParam("id") Integer did, Model model) {
        Direction direction = directionService.queryDirectionById(did);
        model.addAttribute("direction", direction);
        return "/server/direction/addDirection";
    }

    // 修改研究方向
    @PutMapping("/saveDirection")
    public String updateDirection(Direction direction) {
        int result = directionService.updateDirection(direction);
        if (result == 1) {
            msg = "修改成功";
        } else {
            msg = "修改失败";
        }
        return "redirect:directionList";
    }
}
