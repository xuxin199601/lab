package com.csu.lab.controller;

import com.csu.lab.pojo.Project;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ProjectService;
import com.csu.lab.service.ResearcherService;
import com.csu.lab.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/server")
public class FormManageController {

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/researcher_management")
    public String getResearcherManagement(@RequestParam(defaultValue = "0")Integer personType,
                                          ModelMap modelMap) {
        List<Researcher> researcherList = researcherService.getResearcherList(personType);
        modelMap.addAttribute("researchers", researcherList);
        return "researcher_management";
    }

    @RequestMapping("/thesis_management")
    public String getThesisManagement(ModelMap modelMap) {
        List<Thesis> thesisList = thesisService.getThesisList();
        modelMap.addAttribute("thesisList", thesisList);
        return "thesis_management";
    }

    @RequestMapping("/project_management")
    public String getProjectManagement(ModelMap modelMap) {
        List<Project> projectList = projectService.getProjectList();
        modelMap.addAttribute("projectList", projectList);
        return "project_management";
    }

    @RequestMapping("/form_one")
    public String reviewForm() {
        return "form_one";
    }

    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */
}
