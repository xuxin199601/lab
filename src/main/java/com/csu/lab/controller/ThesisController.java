package com.csu.lab.controller;

import com.csu.lab.config.DateConverterConfig;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ThesisService;
import com.csu.lab.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/server/thesis")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    // 分页成果信息列表
    @GetMapping("/thesisList")
    public Message getStudentByPage(@RequestParam("page_index")Integer pageIndex,
                                    @RequestParam("page_size")Integer pageSize) {
        List<Thesis> thesisList = thesisService.queryThesisListPaged(pageIndex, pageSize);
        return Message.success().add(thesisList);
    }

    // 通过id获取成果信息
    @GetMapping("/thesisInfo")
    public Message getTutorById(@RequestParam("tid")Integer rid) {
        Thesis thesis = thesisService.queryThesisById(rid);
        return Message.success().add(thesis);
    }

    // 添加研究生
    @PostMapping("/addThesis")
    public Message addTutor(@RequestParam("name")String name,
                            @RequestParam("abstracts")String abstracts,
                            @RequestParam("keywords")String keywords,
                            @RequestParam("content")String content,
                            @RequestParam("code")String code,
                            @RequestParam("data")String data ,
                            @RequestParam("time")String time) throws Exception {
        Date time_date = DateConverterConfig.parseDate(time, "yyyy-MM-dd");
        Thesis Thesis = new Thesis(name, abstracts, keywords, content, code, data, time_date);
        thesisService.saveThesis(Thesis);
        return Message.success().add("添加成功");
    }

//    // 修改研究生信息
//    @PutMapping("/modifyThesis")
//    public Message modifyTutor(@RequestParam("rid")Integer rid,
//                               @RequestParam("aid")Integer aid,
//                               @RequestParam("name")String name,
//                               @RequestParam("post")String post,
//                               @RequestParam("image")String image,
//                               @RequestParam("person_type")Integer personType,
//                               @RequestParam("affiliated_tutor")Integer affiliatedTutor,
//                               @RequestParam("direction")String direction ,
//                               @RequestParam("introduction")String introduction) throws Exception {
//        Thesis Thesis = new Thesis(rid, aid, name, post, image, personType, affiliatedTutor, direction, introduction);
//        ThesisService.updateThesis(Thesis);
//        return Message.success().add("Success");
//    }

    // 删除研究生信息
    @DeleteMapping("/deleteThesis")
    public Message deleteTuttor(@RequestParam("tid")Integer rid) {
        thesisService.deleteThesis(rid);
        return Message.success().add("Success");
    }

}
