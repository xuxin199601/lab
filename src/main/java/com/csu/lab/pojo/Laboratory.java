package com.csu.lab.pojo;

import javax.persistence.*;

@Table(name = "t_laboratory")
public class Laboratory {
    /**
     * 实验室编号
     */
    @Id
    private Integer lid;

    /**
     * 研究方向
     */
    @Column(name = "res_direction")
    private String resDirection;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 获取实验室编号
     *
     * @return lid - 实验室编号
     */
    public Integer getLid() {
        return lid;
    }

    /**
     * 设置实验室编号
     *
     * @param lid 实验室编号
     */
    public void setLid(Integer lid) {
        this.lid = lid;
    }

    /**
     * 获取研究方向
     *
     * @return res_direction - 研究方向
     */
    public String getResDirection() {
        return resDirection;
    }

    /**
     * 设置研究方向
     *
     * @param resDirection 研究方向
     */
    public void setResDirection(String resDirection) {
        this.resDirection = resDirection;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
}