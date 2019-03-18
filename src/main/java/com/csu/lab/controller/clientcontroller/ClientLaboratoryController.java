package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.Direction;
import com.csu.lab.pojo.Laboratory;
import com.csu.lab.pojo.Message;
import com.csu.lab.service.DirectionService;
import com.csu.lab.service.LaboratoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client/laboratory")
public class ClientLaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private DirectionService directionService;

    // 通过id获取实验室信息
    @RequestMapping("/laboratoryInfo")
    public String getClientLaboratoryById(Model model) {
        List<Laboratory> laboratoryList = laboratoryService.getLaboratoryList();
        List<Direction> directionList = directionService.getDirectionList();

        PageInfo pageInfo = new PageInfo(directionList,10);
        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("laboratory" ,laboratoryList.get(0));
        model.addAttribute("direction" ,directionList.get(0));

        return "client/introduction/index";
    }

    /*
    // 分页实验室信息列表
    @GetMapping("/laboratoryList")
    public Message getStudentByPage(@RequestParam("page_index")Integer pageIndex,
                                    @RequestParam("page_size")Integer pageSize) {
        List<Laboratory> laboratoryList = laboratoryService.queryLaboratoryListPaged(pageIndex, pageSize);
        return Message.success().add(laboratoryList);
    }

    // 通过id获取实验室信息
    @GetMapping("/laboratoryInfo")
    public Message getLaboratoryById(@RequestParam("lid")Integer pid) {
        Laboratory laboratory = laboratoryService.queryLaboratoryById(pid);
        return Message.success().add(laboratory);
    }

    // 添加实验室
    @PostMapping("/addLaboratory")
    public Message addLaboratory(Laboratory laboratory) throws Exception {
        laboratoryService.saveLaboratory(laboratory);
        return Message.success().add("添加成功");
    }

    // 修改实验室信息
    @PutMapping("/modifyLaboratory")
    public Message modifyLaboratory(Laboratory laboratory) {
        laboratoryService.updateLaboratory(laboratory);
        return Message.success().add("Success");
    }

    // 删除实验室信息
    @DeleteMapping("/deleteLaboratory")
    public Message deleteLaboratory(@RequestParam("lid")Integer rid) {
        laboratoryService.deleteLaboratory(rid);
        return Message.success().add("Success");
    }*/
}
