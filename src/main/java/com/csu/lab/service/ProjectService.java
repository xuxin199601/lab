package com.csu.lab.service;

import com.csu.lab.pojo.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectList();

    void saveProject(Project project)throws Exception;

    void updateProject(Project project);

    void deleteProject(Integer projectId);

    Project queryProjectById(Integer projectId);

    // 根据条件查询
    List<Project> queryByProperty(String property, Object value);

}
