package com.csu.lab.pojo;

import org.springframework.data.annotation.Id;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_activity")
public class Activity {
    /**
     * 活动编号
     */
    @Id
    private Integer aid;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动时间
     */
    private Date time;

    /**
     * 活动介绍
     */
    private String introduction;

    /**
     * 获取活动编号
     *
     * @return aid - 活动编号
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置活动编号
     *
     * @param aid 活动编号
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取活动名称
     *
     * @return name - 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置活动名称
     *
     * @param name 活动名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取活动时间
     *
     * @return time - 活动时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置活动时间
     *
     * @param time 活动时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取活动介绍
     *
     * @return introduction - 活动介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置活动介绍
     *
     * @param introduction 活动介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}