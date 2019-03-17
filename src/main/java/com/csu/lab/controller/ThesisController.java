package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/server/thesis")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    // 分页成果信息列表
//    @GetMapping("/thesisList")
//    public Message getStudentByPage(@RequestParam("page_index")Integer pageIndex,
//                                    @RequestParam("page_size")Integer pageSize) {
//        List<Thesis> thesisList = thesisService.queryThesisListPaged(pageIndex, pageSize);
//        return Message.success().add(thesisList);
//    }

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

    /**
     * 跳转到编辑成果界面
     */
    @GetMapping("/thesis/{tid}")
    public String getThesisById(@PathVariable("tid")Integer tid,
                               ModelMap modelMap) {
        Thesis thesis = thesisService.queryThesisById(tid);
        modelMap.addAttribute("thesis", thesis);
        return "thesis_add";
    }

    /**
     * 修改按钮，更新成果信息，返回至list
     */
    @PutMapping("/saveThesis")
    public String saveteThesis(@ModelAttribute Thesis thesis,
                              ModelMap modelMap) {
        thesisService.updateThesis(thesis);
        return "redirect:/server/thesisList";
    }

    /**
     * 添加按钮，添加成果信息，返回至list
     */
    @PostMapping("/saveThesis")
    public String saveThesis(@ModelAttribute Thesis thesis) throws Exception {
        thesisService.saveThesis(thesis);
        return "redirect:/server/thesisList";
    }

    /**
     * 跳转到添加成果页面
     */
    @GetMapping(value = "/addThesis")
    public String toAddPage(){
        return "thesis_add";
    }

    /**
     * 删除成果信息
     */
    @DeleteMapping("/thesis/{tid}")
    public String delThesis(@PathVariable("tid")Integer rid) {
        thesisService.deleteThesis(rid);
        return "redirect:/server/thesisList";
    }
    
}
