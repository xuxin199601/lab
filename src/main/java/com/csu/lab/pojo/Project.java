package com.csu.lab.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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


//    public String getVideo_url() {
//        return video_url;
//    }
//
//    public void setVideo_url(String video_url) {
//        this.video_url = video_url;
//    }

    /**
     * 项目开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 项目截止时间
     */
    @Column(name = "end_time")
    private Date endTime;

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
     * @return gain - 项目工程
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

    /**
     * 获取项目开始时间
     *
     * @return start_time - 项目开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置项目开始时间
     *
     * @param startTime 项目开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取项目截止时间
     *
     * @return end_time - 项目截止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置项目截止时间
     *
     * @param endTime 项目截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}