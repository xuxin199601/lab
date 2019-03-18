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

}