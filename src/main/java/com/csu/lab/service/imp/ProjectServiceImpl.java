package com.csu.lab.service.imp;

import com.csu.lab.customConst.CustomConstant;
import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.ProjectException;
import com.csu.lab.mapper.ProjectMapper;
import com.csu.lab.pojo.Project;
import com.csu.lab.service.ProjectService;
import com.csu.lab.utils.CustomUtils;
import com.github.pagehelper.PageHelper;
import org.omg.CORBA.CustomMarshal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class ProjectServiceImpl implements ProjectService {

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
    public Integer saveProject(Project project) throws IOException {
        logger.info("addProject:{}", project);
        List<Project> projectList = queryByProperty("name", project.getName());
        if (projectList.isEmpty()) {


//            MultipartFile blFile = project.getBlFile();
//            if (!blFile.isEmpty()) {
//                String newFilename = CustomUtils.uploadFile(blFile);
//                project.setVideo(newFilename);
//            } else {
//                project.setVideo("");
//            }

//            int rowNum  = projectMapper.insert(project);
//            if(rowNum==0){
////                return ResultBundle.failure(ResCode.FALSE,"添加一级代理信息失败");
//            }
//            return ResultBundle.success(null);
            if (projectMapper.insert(project) != 1) {
                throw new ProjectException(ResultEnum.PROJECT_SAVE_FAILURE);
            }
        } else {
            throw new ProjectException(ResultEnum.PROJECT_EXIST);
        }

        return projectMapper.insert(project);
    }

    @Override
    public Integer saveProject(Project project, MultipartFile blFile) throws Exception {
        logger.info("addProject:{}", project);
        List<Project> projectList = queryByProperty("name", project.getName());
        if (projectList.isEmpty()) {
//            MultipartFile blFile = project.getBlFile();
            if (!blFile.isEmpty()) {
                String newFilename = CustomUtils.uploadFile(blFile, CustomConstant.VIDEO_SAVE_PATH);
                project.setVideo(newFilename);
//                project.setVideo(path + "/" + newFileName);
            } else if (blFile.getOriginalFilename().equals("")) {
                project.setVideo("");
            } else {
                throw new ProjectException(ResultEnum.PROJECT_VIDEO_FAILURE);
            }

//            int rowNum  = projectMapper.insert(project);
//            if(rowNum==0){
////                return ResultBundle.failure(ResCode.FALSE,"添加一级代理信息失败");
//            }
//            return ResultBundle.success(null);
            if (projectMapper.insert(project) != 1) {
                throw new ProjectException(ResultEnum.PROJECT_SAVE_FAILURE);
            }
        } else {
            throw new ProjectException(ResultEnum.PROJECT_EXIST);
        }
        return projectMapper.insert(project);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateProject(Project project) {
        logger.info("updateProject:{}", project);
        if (projectMapper.updateByPrimaryKeySelective(project) != 1) {
            throw new ProjectException(ResultEnum.PROJECT_UPDATE_FAILURE);
        }
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public Integer updateProject(Project project, MultipartFile blFile) throws IOException {
        logger.info("updateProject:{}", project);

//        如果文件为空，则不修改
        if (!blFile.getOriginalFilename().equals("")) {
            String deleteFilePath = project.getVideo();
            String newFilename = CustomUtils.uploadFile(blFile, CustomConstant.VIDEO_SAVE_PATH);
            project.setVideo(newFilename);
//            删除旧的文件
//            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + CustomConstant.VIDEO_SAVE_PATH;
//            String filename = path+""
//            String deleteFilePath = project.getVideo();
            CustomUtils.deleteFile(deleteFilePath);
        }
        if (projectMapper.updateByPrimaryKeySelective(project) != 1) {
            throw new ProjectException(ResultEnum.PROJECT_UPDATE_FAILURE);
        }

        return projectMapper.updateByPrimaryKeySelective(project);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteProject(Integer projectId) {
        logger.info("deleteProjectById:{}", projectId);
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (projectMapper.deleteByPrimaryKey(projectId) != 1) {
            throw new ProjectException(ResultEnum.PROJECT_DELETE_FAILURE);
        } else {
            CustomUtils.deleteFile(project.getVideo());
        }
        return projectMapper.deleteByPrimaryKey(projectId);
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
        criteria.andLike(property, "%" + value + "%");
        example.and(criteria);

        return projectMapper.selectByExample(example);
    }
}
