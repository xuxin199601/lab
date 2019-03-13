package com.csu.lab.service;

import com.csu.lab.pojo.Project;

import java.util.List;

public interface ProjectService {

    public List<Project> getProjectList();

    public void saveProject(Project project)throws Exception;

    public void updateProject(Project project);

    public void deleteProject(Integer projectId);

    public Project queryProjectById(Integer projectId);

    // 分页查询
    public List<Project> queryProjectListPaged(Integer page, Integer pageSize);

    // 根据条件查询
    public List<Project> queryByProperty(String property, Object value);

}
