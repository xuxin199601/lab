package com.csu.lab.pojo;

import javax.persistence.*;

@Table(name = "t_researcher")
public class Researcher {
    /**
     * 研究员编号
     */
    @Id
    private Integer rid;

    /**
     * 账户编号
     */
    private Integer aid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职称
     */
    private String post;

    /**
     * 头像图片链接
     */
    private String image;

    /**
     * 人员类别；0:导师，1:硕士生，2:博士生，3:其他
     */
//    @Column(name = "person_type")
    private Integer personType;

    /**
     * 学生隶属导师编号
     */
//    @Column(name = "affiliated_tutor")
    private Integer affiliatedTutor = -1;

    /**
     * 研究方向
     */
    private String direction;

    /**
     * 人员介绍
     */
    private String introduction;

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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取职称
     *
     * @return post - 职称
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置职称
     *
     * @param post 职称
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取头像图片链接
     *
     * @return image - 头像图片链接
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置头像图片链接
     *
     * @param image 头像图片链接
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取人员类别；0:导师，1:硕士生，2:博士生，3:其他
     *
     * @return person_type - 人员类别；0:导师，1:硕士生，2:博士生，3:其他
     */
    public Integer getPersonType() {
        return personType;
    }

    /**
     * 设置人员类别；0:导师，1:硕士生，2:博士生，3:其他
     *
     * @param personType 人员类别；0:导师，1:硕士生，2:博士生，3:其他
     */
    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    /**
     * 获取学生隶属导师编号
     *
     * @return affiliated_tutor - 学生隶属导师编号
     */
    public Integer getAffiliatedTutor() {
        return affiliatedTutor;
    }

    /**
     * 设置学生隶属导师编号
     *
     * @param affiliatedTutor 学生隶属导师编号
     */
    public void setAffiliatedTutor(Integer affiliatedTutor) {
        this.affiliatedTutor = affiliatedTutor;
    }

    /**
     * 获取研究方向
     *
     * @return direction - 研究方向
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 设置研究方向
     *
     * @param direction 研究方向
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * 获取人员介绍
     *
     * @return introduction - 人员介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置人员介绍
     *
     * @param introduction 人员介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Researcher{" +
                "rid=" + rid +
                ", aid=" + aid +
                ", name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", image='" + image + '\'' +
                ", personType=" + personType +
                ", affiliatedTutor=" + affiliatedTutor +
                ", direction='" + direction + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}