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
                                   Model model,
                                   HttpSession session) {

        session.setAttribute("error",msg);
        msg = null;

        PageHelper.startPage(pageNum,pageSize);

        List<Direction> directionList = directionService.getDirectionList();
        PageInfo pageInfo = new PageInfo(directionList,10);

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
        return "server/direction/directionManage";
    }

    // 通过id获取研究方向信息
    @GetMapping("/directionInfo")
    public Message getDirectionById(@RequestParam("did")Integer pid) {
        Direction direction = directionService.queryDirectionById(pid);
        return Message.success().add(direction);
    }

    //跳转到添加界面
    @RequestMapping("/doAddDirection")
    public String doAddDirection(){
        return "server/direction/addDirection";
    }

    // 添加研究方向
    @PostMapping("/addDirection")
    public String addDirection(Direction direction) {
        int result = directionService.addDirection(direction);
        if (result == 1) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return "redirect:directionList";
    }

    // 修改研究方向信息
    @PutMapping("/modifyDirection")
    public Message modifyDirection(Direction direction) {
        directionService.updateDirection(direction);
        return Message.success().add("Success");
    }

    // 删除研究方向信息
    @DeleteMapping("/deleteDirection")
    public Message deleteDirection(@RequestParam("did")Integer rid) {
        directionService.deleteDirection(rid);
        return Message.success().add("Success");
    }
}
