package com.csu.lab.controller;

import com.csu.lab.pojo.Project;
import com.csu.lab.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 项目管理
 */
@Controller
@RequestMapping("/server/project")
public class ProjectController {

    String msg;

    @Autowired
    ProjectService projectService;

    // 跳转到账户管理界面
    @RequestMapping("/projectList")
    public String projectList(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "value", required = false) String value,
                              Model model,
                              HttpSession session) {

        session.setAttribute("error", msg);
        msg = null;

        PageHelper.startPage(pageNum, pageSize);

        List<Project> list;
        if (value != null) {
            model.addAttribute("key", value);
            list = projectService.queryByProperty("name", value);
        } else {
            list = projectService.getProjectList();
        }

        PageInfo pageInfo = new PageInfo(list, 10);

        model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "server/gain/projectManage";
    }

    // 保存修改的账户信息，跳转到账户管理界面
    @RequestMapping(value = "/saveProject", method = RequestMethod.PUT)
    public String saveEditProject(Project project, @RequestParam("blFile") MultipartFile blFile) throws IOException {
        int result = projectService.updateProject(project, blFile);
        if (result == 1) {
            msg = "修改成功";
        } else {
            msg = "修改失败";
        }
        return "redirect:/server/project/projectList";
    }

    // 保存添加的账户信息，跳转到账户管理界面
    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String saveProject(Project project, @RequestParam("blFile") MultipartFile blFile) throws Exception {
        int result = projectService.saveProject(project, blFile);
        if (result == 1) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return "redirect:/server/project/projectList";
    }

    // 删除账户信息，跳转到账户管理界面
    @RequestMapping("/deleteProject")
    public String delProject(@RequestParam("id") Integer pid) {
        int result = projectService.deleteProject(pid);
        if (result == 1) {
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }
        return "redirect:/server/project/projectList";
    }

    // 跳转到新增账户页面
    @RequestMapping("/addProject")
    public String toAddPage() {
        return "server/gain/addProject";
    }

    // 跳转到修改账户页面
    @RequestMapping("/editProject")
    public String toEditPage(@RequestParam("id") Integer pid,
                             Model model) {
        Project project = projectService.queryProjectById(pid);
        model.addAttribute("project", project);
        return "server/gain/addProject";
    }

    @RequestMapping("/detailProject")
    public String detailProject(@RequestParam("id") Integer pid,
                                Model model) {
        Project project = projectService.queryProjectById(pid);
        model.addAttribute("project", project);
        model.addAttribute("detail", true);
        return "server/gain/addProject";
    }
}
