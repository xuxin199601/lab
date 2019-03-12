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
