package com.csu.lab.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_account")
public class Account {
    /**
     * 账户编号
     */
    @Id
    private Integer aid;

    /**
     * 账户姓名；账号使用学号
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 管理权限；0:超级管理员，1:研究人员，2:普通用户
     */
    private Integer privileges;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取账户编号
     *
     * @return aid - 账户编号
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置账户编号
     *
     * @param aid 账户编号
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取账户姓名；账号使用学号
     *
     * @return username - 账户姓名；账号使用学号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置账户姓名；账号使用学号
     *
     * @param username 账户姓名；账号使用学号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取管理权限；0:超级管理员，1:研究人员，2:普通用户
     *
     * @return privileges - 管理权限；0:超级管理员，1:研究人员，2:普通用户
     */
    public Integer getPrivileges() {
        return privileges;
    }

    /**
     * 设置管理权限；0:超级管理员，1:研究人员，2:普通用户
     *
     * @param privileges 管理权限；0:超级管理员，1:研究人员，2:普通用户
     */
    public void setPrivileges(Integer privileges) {
        this.privileges = privileges;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}