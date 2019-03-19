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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/server/thesis")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    // 分页成果信息列表
    @RequestMapping("/thesisList")
    public String getStudentByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "value",required = false) String value,
                                    Model model) {

        PageHelper.startPage(pageNum,pageSize);

//        List<Thesis> thesisList

//                = thesisService.getThesisList();
        List<Thesis> thesisList;
        if (value != null){
            model.addAttribute("key", value);
            thesisList = thesisService.queryByProperty("name", value);
        }else {
            thesisList = thesisService.getThesisList();
        }
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
    @RequestMapping("/thesisInfo")
    public Message getThesisById(@RequestParam("tid")Integer rid) {
        Thesis thesis = thesisService.queryThesisById(rid);
        return Message.success().add(thesis);
    }


    @RequestMapping("/addThesis")
    public String addThesis() {
        return "server/gain/addThesis";
    }

    @RequestMapping("/editThesis")
    public String toEditPage(@RequestParam("id")Integer aid,
                             Model model) {
        Thesis thesis = thesisService.queryThesisById(aid);
        model.addAttribute("thesis", thesis);
        return "server/gain/addThesis";
    }







    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */
    // 添加成果
    @RequestMapping(value = "/saveThesis",method=RequestMethod.POST)
    public String saveThesis(Thesis thesis) throws Exception {
//        Date time_date = DateConverterConfig.parseDate(time, "yyyy-MM-dd");
//        Thesis Thesis = new Thesis(name, abstracts, keywords, content, code, data, time_date);
        thesisService.saveThesis(thesis);
        return "redirect:/server/thesis/thesisList";
    }

    // 修改成果信息
    @RequestMapping(value = "/saveThesis",method = RequestMethod.PUT)
    public String modifyThesis(Thesis thesis) {
        thesisService.updateThesis(thesis);
        return "redirect:/server/thesis/thesisList";
    }

    // 删除成果信息
    @RequestMapping("/deleteThesis")
    public String deleteTuttor(@RequestParam("id")Integer tid) {
        thesisService.deleteThesis(tid);
        return "redirect:/server/thesis/thesisList";
    }

    /**
     * 通过验证的用户下载论文
     */
    @GetMapping("/thesisContent/{tid}/{aid}")
    public void getThesisContent(@PathVariable Integer tid, @PathVariable Integer aid, HttpServletResponse response, HttpServletRequest request){
        thesisService.getThesisContent(tid,aid,response,request);
    }

    /**
     * 通过验证的用户下载论文代码
     */
    @GetMapping("/thesisCode/{tid}/{aid}")
    public void getThesisCode(@PathVariable Integer tid,@PathVariable Integer aid, HttpServletResponse response,HttpServletRequest request){
        thesisService.getThesisCode(tid,aid,response,request);
    }

    /**
     * 通过验证的用户下载论文数据集（考虑没有数据集的情况）
     */
    @GetMapping("/thesisData/{tid}/{aid}")
    public void getThesisData(@PathVariable Integer tid,@PathVariable Integer aid, HttpServletResponse response,HttpServletRequest request){
        thesisService.getThesisData(tid,aid,response,request);
    }

}
