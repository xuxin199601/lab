package com.csu.lab.controller;

import com.csu.lab.pojo.Laboratory;
import com.csu.lab.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/server/laboratory")
public class LaboratoryController {

    String msg;

    @Autowired
    private LaboratoryService laboratoryService;

    // 实验室信息展示，只有单条信息，只获取第一条数据
    @RequestMapping("/laboratoryList")
    public String getStudentByPage(Model model) {
        List<Laboratory> list = laboratoryService.getLaboratoryList();

        model.addAttribute("laboratory", list.get(0));
        model.addAttribute("error",msg);
        msg = null;
        return "server/laboratory/laboratoryManage";
    }

    // 修改实验室信息
    @PostMapping("/modifyLaboratory")
    public String modifyLaboratory(Laboratory laboratory) {

        System.out.println(laboratory.getLid());

        int result = laboratoryService.updateLaboratory(laboratory);

        if (result == 1) {
            msg = "修改成功";
        } else {
            msg = "修改失败";
        }
        return "redirect:laboratoryList";
    }
}
