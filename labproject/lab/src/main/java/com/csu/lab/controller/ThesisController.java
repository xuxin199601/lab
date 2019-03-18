package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Project;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ThesisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    // 分页成果信息列表
    @GetMapping("/server/thesis/thesisList")
    public String getStudentByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    Model model) {

        PageHelper.startPage(pageNum,pageSize);

        List<Thesis> thesisList = thesisService.getThesisList();

        PageInfo pageInfo = new PageInfo(thesisList,10);

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
        return "server/gain/thesisManage";
    }

    // 通过id获取成果信息
    @GetMapping("/thesisInfo")
    public Message getThesisById(@RequestParam("tid")Integer rid) {
        Thesis thesis = thesisService.queryThesisById(rid);
        return Message.success().add(thesis);
    }

    // 添加成果
    @PostMapping("/addThesis")
    public Message addThesis(Thesis thesis) throws Exception {
//        Date time_date = DateConverterConfig.parseDate(time, "yyyy-MM-dd");
//        Thesis Thesis = new Thesis(name, abstracts, keywords, content, code, data, time_date);
        thesisService.saveThesis(thesis);
        return Message.success().add("添加成功");
    }

    // 修改成果信息
    @PutMapping("/modifyThesis")
    public Message modifyThesis(Thesis thesis) {
        thesisService.updateThesis(thesis);
        return Message.success().add("Success");
    }

    // 删除成果信息
    @DeleteMapping("/deleteThesis")
    public Message deleteTuttor(@RequestParam("tid")Integer rid) {
        thesisService.deleteThesis(rid);
        return Message.success().add("Success");
    }

}
