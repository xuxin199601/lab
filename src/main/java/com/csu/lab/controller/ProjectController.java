package com.csu.lab.controller;

import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Project;
import com.csu.lab.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目管理
 */
@Controller
@RequestMapping("/server/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    // 跳转到账户管理界面
    @RequestMapping("/projectList")
    public String projectList(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "value", required = false) String value,
                              Model model) {

        PageHelper.startPage(pageNum,pageSize);

        List<Project> list;
        if (value != null){
            model.addAttribute("key", value);
            list = projectService.queryByProperty("name", value);
        }else {
            list = projectService.getProjectList();
        }

        PageInfo pageInfo = new PageInfo(list,10);

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
        return "server/gain/projectManage";
    }

    // 跳转到新增账户页面
    @RequestMapping("/addProject")
    public String toAddPage() {
        return "server/gain/addProject";
    }

    // 跳转到修改账户页面
    @RequestMapping("/editProject")
    public String toEditPage(@RequestParam("id")Integer pid,
                             Model model) {
        Project project = projectService.queryProjectById(pid);
        model.addAttribute("project", project);
        return "server/gain/addProject";
    }

    // 保存添加的账户信息，跳转到账户管理界面
    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String saveProject(Project project) throws Exception {
        projectService.saveProject(project);
        return "redirect:/server/project/projectList";
    }

    // 保存修改的账户信息，跳转到账户管理界面
    @RequestMapping(value = "/saveProject", method = RequestMethod.PUT)
    public String saveEditProject(Project project) {
        projectService.updateProject(project);
        return "redirect:/server/project/projectList";
    }

    // 删除账户信息，跳转到账户管理界面
    @RequestMapping("/deleteProject")
    public String delProject(@RequestParam("id")Integer pid) {
        projectService.deleteProject(pid);
        return "redirect:/server/project/projectList";
    }
}
