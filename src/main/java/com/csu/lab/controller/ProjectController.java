package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Project;
import com.csu.lab.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/Project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // 分页项目信息列表
    @GetMapping("/projectList")
    public Message getStudentByPage(@RequestParam("page_index")Integer pageIndex,
                                    @RequestParam("page_size")Integer pageSize) {
        List<Project> projectList = projectService.queryProjectListPaged(pageIndex, pageSize);
        return Message.success().add(projectList);
    }

    // 通过id获取项目信息
    @GetMapping("/projectInfo")
    public Message getProjectById(@RequestParam("pid")Integer pid) {
        Project project = projectService.queryProjectById(pid);
        return Message.success().add(project);
    }

    // 添加项目
    @PostMapping("/addProject")
    public Message addProject(Project project) throws Exception {
        projectService.saveProject(project);
        return Message.success().add("添加成功");
    }

    // 修改项目信息
    @PutMapping("/modifyProject")
    public Message modifyProject(Project project) {
        projectService.updateProject(project);
        return Message.success().add("Success");
    }

    // 删除项目信息
    @DeleteMapping("/deleteProject")
    public Message deleteProject(@RequestParam("pid")Integer rid) {
        projectService.deleteProject(rid);
        return Message.success().add("Success");
    }

}
