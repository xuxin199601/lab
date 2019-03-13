package com.csu.lab.pojo;

import org.springframework.data.annotation.Id;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_thesis")
public class Thesis {
    /**
     * 成果编号
     */
    @Id
    private Integer tid;

    /**
     * 论文名称
     */
    private String name;

    /**
     * 摘要
     */
    private String abstracts;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 代码
     */
    private String code;

    /**
     * 数据
     */
    private String data;

    /**
     * 论文发表时间
     */
    private Date time;

    public Thesis() {
    }

    /**
     * 添加成果信息所用的构造函数
     */
    public Thesis(String name, String abstracts, String keywords, String content, String code, String data, Date time) {
        this.name = name;
        this.abstracts = abstracts;
        this.keywords = keywords;
        this.content = content;
        this.code = code;
        this.data = data;
        this.time = time;
    }

    /**
     * 修改成果信息所用的构造函数
     */
    public Thesis(Integer tid, String name, String abstracts, String keywords, String content, String code, String data, Date time) {
        this.tid = tid;
        this.name = name;
        this.abstracts = abstracts;
        this.keywords = keywords;
        this.content = content;
        this.code = code;
        this.data = data;
        this.time = time;
    }

    /**
     * 获取成果编号
     *
     * @return tid - 成果编号
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置成果编号
     *
     * @param tid 成果编号
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 获取论文名称
     *
     * @return name - 论文名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置论文名称
     *
     * @param name 论文名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取摘要
     *
     * @return abstract - 摘要
     */
    public String getAbstracts() {
        return abstracts;
    }

    /**
     * 设置摘要
     *
     * @param abstracts 摘要
     */
    public void setAbstract(String abstracts) {
        this.abstracts = abstracts;
    }

    /**
     * 获取关键字
     *
     * @return keywords - 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords 关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取文章正文
     *
     * @return content - 文章正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章正文
     *
     * @param content 文章正文
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取数据
     *
     * @return data - 数据
     */
    public String getData() {
        return data;
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 获取论文发表时间
     *
     * @return time - 论文发表时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置论文发表时间
     *
     * @param time 论文发表时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}