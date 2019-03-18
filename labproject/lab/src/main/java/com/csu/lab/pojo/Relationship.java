package com.csu.lab.pojo;

import javax.persistence.*;

@Table(name = "t_relationship")
public class Relationship {
    /**
     * 关系编号
     */
    @Id
    private Integer rsid;

    /**
     * 研究员编号
     */
    private Integer rid;

    /**
     * 项目编号
     */
    private Integer pid;

    /**
     * 研究成果编号
     */
    private Integer tid;

    /**
     * 获取关系编号
     *
     * @return rsid - 关系编号
     */
    public Integer getRsid() {
        return rsid;
    }

    /**
     * 设置关系编号
     *
     * @param rsid 关系编号
     */
    public void setRsid(Integer rsid) {
        this.rsid = rsid;
    }

    /**
     * 获取研究员编号
     *
     * @return rid - 研究员编号
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置研究员编号
     *
     * @param rid 研究员编号
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

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
     * 获取研究成果编号
     *
     * @return tid - 研究成果编号
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置研究成果编号
     *
     * @param tid 研究成果编号
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }
}