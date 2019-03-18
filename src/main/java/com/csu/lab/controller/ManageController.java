package com.csu.lab.controller;

import com.csu.lab.pojo.*;
import com.csu.lab.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/server")
public class ManageController {

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private DirectionService directionService;

    @Autowired
    private LaboratoryService laboratoryService;

    /**
     * 跳转到导师信息列表
     */
    @GetMapping("/tutorList")
    public String getResearcherManagement(ModelMap modelMap,
                                          @RequestParam(name = "value", required = false) Integer value,
                                          @RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {

        List<Researcher> researcherList = new ArrayList<>();
        if (value != null){
            modelMap.addAttribute("key", value);
            Researcher researcher = researcherService.queryResearcherById(value);
            researcherList.add(researcher);
            modelMap.addAttribute("researcherList", researcherList);
        }else {
            researcherList = researcherService.queryResearcherListPaged(0, pageNum, pageSize);
            modelMap.addAttribute("researcherList", researcherList);
        }

        // 分页设置
        PageInfo pageInfo = new PageInfo<Researcher>(researcherList, 5);
        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("pageNum", pageInfo.getPageNum());
        modelMap.addAttribute("pageSize", pageInfo.getPageSize());
        modelMap.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        modelMap.addAttribute("totalPages", pageInfo.getPages());
        modelMap.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "tutor_list";
    }

    /**
     * 跳转到研究生信息列表
     */
    @RequestMapping("/studentList")
    public String getStudentManagement(ModelMap modelMap,
                                       @RequestParam(name = "value", required = false) Integer value,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Researcher> researcherList = new ArrayList<>();
        if (value != null){
            modelMap.addAttribute("key", value);
            Researcher researcher = researcherService.queryResearcherById(value);
            researcherList.add(researcher);
            modelMap.addAttribute("researcherList", researcherList);
        }else {
            researcherList = researcherService.queryStudentListPaged(pageNum, pageSize);
            modelMap.addAttribute("researcherList", researcherList);
        }

        // 分页设置
        PageInfo pageInfo = new PageInfo<Researcher>(researcherList, 5);
        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("pageNum", pageInfo.getPageNum());
        modelMap.addAttribute("pageSize", pageInfo.getPageSize());
        modelMap.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        modelMap.addAttribute("totalPages", pageInfo.getPages());
        modelMap.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "student_list";
    }

    /**
     * 跳转到成果信息列表
     */
    @RequestMapping("/thesisList")
    public String getThesisManagement(ModelMap modelMap,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Thesis> thesisList = thesisService.queryThesisListPaged(pageNum, pageSize);
        modelMap.addAttribute("thesisList", thesisList);

        // 分页设置
        PageInfo pageInfo = new PageInfo<Thesis>(thesisList, 5);
        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("pageNum", pageInfo.getPageNum());
        modelMap.addAttribute("pageSize", pageInfo.getPageSize());
        modelMap.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        modelMap.addAttribute("totalPages", pageInfo.getPages());
        modelMap.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "thesis_list";
    }

    /**
     * 跳转到项目信息列表
     */
    @RequestMapping("/projectList")
    public String getProjectManagement(ModelMap modelMap,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Project> projectList = projectService.queryProjectListPaged(pageNum, pageSize);
        modelMap.addAttribute("projectList", projectList);

        // 分页设置
        PageInfo pageInfo = new PageInfo<Project>(projectList, 5);
        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("pageNum", pageInfo.getPageNum());
        modelMap.addAttribute("pageSize", pageInfo.getPageSize());
        modelMap.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        modelMap.addAttribute("totalPages", pageInfo.getPages());
        modelMap.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "project_list";
    }

    /**
     * 跳转到方向信息列表
     */
    @RequestMapping("/directiontList")
    public String getDirectionManagement(ModelMap modelMap,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Direction> directiontList = directionService.queryDirectionListPaged(pageNum, pageSize);
        modelMap.addAttribute("directiontList", directiontList);

        // 分页设置
        PageInfo pageInfo = new PageInfo<Direction>(directiontList, 5);
        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("pageNum", pageInfo.getPageNum());
        modelMap.addAttribute("pageSize", pageInfo.getPageSize());
        modelMap.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        modelMap.addAttribute("totalPages", pageInfo.getPages());
        modelMap.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "direction_list";
    }

    /**
     * 跳转到活动信息列表
     */
    @RequestMapping("/activityList")
    public String getActivityManagement(ModelMap modelMap,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Activity> activityList = activityService.queryActivityListPaged(pageNum, pageSize);
        modelMap.addAttribute("activityList", activityList);

        // 分页设置
        PageInfo pageInfo = new PageInfo<Activity>(activityList, 5);
        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("pageNum", pageInfo.getPageNum());
        modelMap.addAttribute("pageSize", pageInfo.getPageSize());
        modelMap.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        modelMap.addAttribute("totalPages", pageInfo.getPages());
        modelMap.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "activity_list";
    }

    /**
     * 跳转到实验室信息列表
     */
    @RequestMapping("/labList")
    public String getLabManagement(ModelMap modelMap,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Laboratory> laboratoryList = laboratoryService.queryLaboratoryListPaged(pageNum, pageSize);
        modelMap.addAttribute("labList", laboratoryList);

        // 分页设置
        PageInfo pageInfo = new PageInfo<Laboratory>(laboratoryList, 5);
        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("pageNum", pageInfo.getPageNum());
        modelMap.addAttribute("pageSize", pageInfo.getPageSize());
        modelMap.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        modelMap.addAttribute("totalPages", pageInfo.getPages());
        modelMap.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "lab_list";
    }

}
