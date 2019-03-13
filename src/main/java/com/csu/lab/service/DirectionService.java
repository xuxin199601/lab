package com.csu.lab.service;

import com.csu.lab.pojo.Project;

import java.util.List;

public interface DirectionService {

    public List<Project> getProjectList();

    public void saveProject(Project project)throws Exception;

    public void updateProject(Project project);

    public void deleteProject(Integer projectId);

    public Project queryProjectById(Integer projectId);

    // 根据对象进行分页查询用户
    public List<Project> queryProjectListPaged(Integer page, Integer pageSize);

    // 根据条件查询
    public List<Project> queryByProperty(String property, Object value);

}
