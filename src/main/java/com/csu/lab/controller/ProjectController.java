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
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // 分页项目信息列表
    @GetMapping("/server/project/projectList")
    public String getStudentByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    Model model) {

        PageHelper.startPage(pageNum,pageSize);

        List<Project> projectList = projectService.getProjectList();

        PageInfo pageInfo = new PageInfo(projectList,10);

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

    // 通过id获取项目信息
    @GetMapping("/server/project/projectInfo")
    public Message getProjectById(@RequestParam("pid")Integer pid) {
        Project project = projectService.queryProjectById(pid);
        return Message.success().add(project);
    }

    // 添加项目
    @PostMapping("/server/project/addProject")
    public Message addProject(Project project) throws Exception {
        projectService.saveProject(project);
        return Message.success().add("添加成功");
    }

    // 修改项目信息
    @PutMapping("/server/project/modifyProject")
    public Message modifyProject(Project project) {
        projectService.updateProject(project);
        return Message.success().add("Success");
    }

    // 删除项目信息
    @DeleteMapping("/server/project/deleteProject")
    public Message deleteProject(@RequestParam("pid")Integer rid) {
        projectService.deleteProject(rid);
        return Message.success().add("Success");
    }

    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */

}
