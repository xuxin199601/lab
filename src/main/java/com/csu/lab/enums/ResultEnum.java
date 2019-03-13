package com.csu.lab.enums;

public enum ResultEnum {
    UNKONW_ERROR(-1, "系统异常"),

    SUCCESS(0, "成功"),

    ACCOUNT_OR_PASSWORD_ERROR(1, "账号或密码错误"),

    ACCOUNT_OR_PASSWORD_IS_SPACE(1, "账号或密码不能为空"),
    //   添加账户时出现错误
    ACCOUNT_EXIST(1, "Username Exist"),
    ACCOUNT_FAILURE(1, "Failure"),

    // 保存导师、研究生信息时的错误
    TUTOR_EXIST(1, "Tutor Exist."),
    RESEARCHER_SAVE_FAILURE(2, "Failure."),
    // 修改导师、研究生信息时的错误
    RESEARCHER_UPDATE_FAILURE(1, "Failure."),
    // 查询导师、研究生信息时的错误
    RESEARCHER_NO_FOUND(1, "Tutor No Found."),
    // 删除导师、研究生信息时的错误
    RESEARCHER_DELETE_FAILURE(1, "Failure."),


    // 保存成果信息时的错误
    THESIS_EXIST(1, "Thesis Exist."),
    THESIS_SAVE_FAILURE(2, "Failure."),
    // 修改导师、研究生信息时的错误
    THESIS_UPDATE_FAILURE(1, "Failure."),
    // 查询导师、研究生信息时的错误
    THESIS_NO_FOUND(1, "Thesis No Found."),
    // 删除导师、研究生信息时的错误
    THESIS_DELETE_FAILURE(1, "Failure."),


    // 保存项目信息时的错误
    PROJECT_EXIST(1, "Project Exist."),
    PROJECT_SAVE_FAILURE(2, "Failure."),
    // 修改项目信息时的错误
    PROJECT_UPDATE_FAILURE(1, "Failure."),
    // 查询项目信息时的错误
    PROJECT_NO_FOUND(1, "Project No Found."),
    // 删除项目信息时的错误
    PROJECT_DELETE_FAILURE(1, "Failure."),

    // 保存活动信息时的错误
    ACTIVITY_EXIST(1, "Activity Exist."),
    ACTIVITY_SAVE_FAILURE(2, "Failure."),
    // 修改活动信息时的错误
    ACTIVITY_UPDATE_FAILURE(1, "Failure."),
    // 查询活动信息时的错误
    ACTIVITY_NO_FOUND(1, "Activity No Found."),
    // 删除活动信息时的错误
    ACTIVITY_DELETE_FAILURE(1, "Failure."),

    // 保存研究方向信息时的错误
    DIRECTION_EXIST(1, "Direction Exist."),
    DIRECTION_SAVE_FAILURE(2, "Failure."),
    // 修改研究方向信息时的错误
    DIRECTION_UPDATE_FAILURE(1, "Failure."),
    // 查询研究方向信息时的错误
    DIRECTION_NO_FOUND(1, "Direction No Found."),
    // 删除研究方向信息时的错误
    DIRECTION_DELETE_FAILURE(1, "Failure."),

    // 保存实验室信息时的错误
    LABORATORY_EXIST(1, "Laboratory Exist."),
    LABORATORY_SAVE_FAILURE(2, "Failure."),
    // 修改实验室信息时的错误
    LABORATORY_UPDATE_FAILURE(1, "Failure."),
    // 查询实验室信息时的错误
    LABORATORY_NO_FOUND(1, "Laboratory No Found."),
    // 删除实验室信息时的错误
    LABORATORY_DELETE_FAILURE(1, "Failure."),

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
