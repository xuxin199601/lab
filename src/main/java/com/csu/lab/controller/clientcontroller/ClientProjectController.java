package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.Project;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client/project")
public class ClientProjectController {

    @Autowired
    ProjectService projectService;

    // 跳转到账户管理界面
    @RequestMapping("/projectList")
    public String projectList(@RequestParam(name = "page",defaultValue = "1") Integer pageNum,
                              Model model,
                              HttpSession session) {

        PageHelper.startPage(pageNum, 12);

        List<Project> list=projectService.getProjectList();

        PageInfo pageInfo = new PageInfo(list, 10);

        model.addAttribute("pageInfo", pageInfo);
//        //获得当前页
//        model.addAttribute("pageNum", pageInfo.getPageNum());
//        //获得一页显示的条数
//        model.addAttribute("pageSize", pageInfo.getPageSize());
//        //是否是第一页
//        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
//        //获得总页数
//        model.addAttribute("totalPages", pageInfo.getPages());
//        //是否是最后一页
//        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        int recordNum=list.size();
        model.addAttribute("size", recordNum);
        model.addAttribute("page", pageNum);
        return "client/project/index";
    }

    // 通过id获取成果信息
    @RequestMapping("/project")
    public String getThesisById(@RequestParam("id") int tid,Model model) {
        Project project = projectService.queryProjectById(tid);
        model.addAttribute("project",project);

        return "client/project/project";
    }
}
