package com.csu.lab.pojo;

import javax.persistence.*;

@Table(name = "t_direction")
public class Direction {
    /**
     * 研究方向编号
     */
    @Id
    private Integer did;

    /**
     * 研究方向
     */
    @Column(name = "res_direction")
    private String resDirection;

    /**
     * 研究方向详情
     */
    private String details;

    /**
     * 获取研究方向编号
     *
     * @return did - 研究方向编号
     */
    public Integer getDid() {
        return did;
    }

    /**
     * 设置研究方向编号
     *
     * @param did 研究方向编号
     */
    public void setDid(Integer did) {
        this.did = did;
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
     * 获取研究方向详情
     *
     * @return details - 研究方向详情
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置研究方向详情
     *
     * @param details 研究方向详情
     */
    public void setDetails(String details) {
        this.details = details;
    }
}