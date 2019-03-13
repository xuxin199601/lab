package com.csu.lab.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Table(name = "t_project")
public class Project {
    /**
     * 项目编号
     */
    @Id
    private Integer pid;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目简介
     */
    private String introduction;

    /**
     * 项目工程
     */
    private String project;

    /**
     * 项目视频
     */
    private String video;

    /**
     * 获取项目编号
     *
     * @return pid - 项目编号
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置项目编号
     *
     * @param pid 项目编号
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取项目简介
     *
     * @return introduction - 项目简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置项目简介
     *
     * @param introduction 项目简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取项目工程
     *
     * @return project - 项目工程
     */
    public String getProject() {
        return project;
    }

    /**
     * 设置项目工程
     *
     * @param project 项目工程
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * 获取项目视频
     *
     * @return video - 项目视频
     */
    public String getVideo() {
        return video;
    }

    /**
     * 设置项目视频
     *
     * @param video 项目视频
     */
    public void setVideo(String video) {
        this.video = video;
    }
}