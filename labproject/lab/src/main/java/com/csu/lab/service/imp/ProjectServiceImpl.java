package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.ProjectException;
import com.csu.lab.mapper.ProjectMapper;
import com.csu.lab.pojo.Project;
import com.csu.lab.service.ProjectService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService{

    private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> getProjectList() {
        return projectMapper.selectAll();
    }

    /**
     * 保存项目信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveProject(Project project) {
        logger.info("addProject:{}", project);
        List<Project> projectList = queryByProperty("name", project.getName());
        if (projectList.isEmpty()) {
            if(projectMapper.insert(project) != 1) {
                throw new ProjectException(ResultEnum.PROJECT_SAVE_FAILURE);
            }
        } else {
            throw new ProjectException(ResultEnum.PROJECT_EXIST);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProject(Project project) {
        logger.info("updateProject:{}", project);
        if(projectMapper.updateByPrimaryKeySelective(project) != 1) {
            throw new ProjectException(ResultEnum.PROJECT_UPDATE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProject(Integer projectId) {
        logger.info("deleteProjectById:{}", projectId);
        if(projectMapper.deleteByPrimaryKey(projectId) != 1) {
            throw new ProjectException(ResultEnum.PROJECT_DELETE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Project queryProjectById(Integer projectId) {
        logger.info("queryProjectById:{}", projectId);
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project == null) {
            throw new ProjectException(ResultEnum.PROJECT_NO_FOUND);
        }
        return project;
    }

    @Override
    public List<Project> queryByProperty(String property, Object value) {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo(property, value);
        example.and(criteria);

        return projectMapper.selectByExample(example);
    }
}
