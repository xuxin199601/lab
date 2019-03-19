package com.csu.lab.service;

import com.csu.lab.pojo.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProjectService {

    List<Project> getProjectList();

    Integer saveProject(Project project)throws Exception;
    Integer saveProject(Project project, MultipartFile blFile)throws Exception;
    Integer updateProject(Project project);
    Integer updateProject(Project project,MultipartFile blFile) throws IOException;
    Integer deleteProject(Integer projectId);

    Project queryProjectById(Integer projectId);

    // 根据条件查询
    List<Project> queryByProperty(String property, Object value);

}
