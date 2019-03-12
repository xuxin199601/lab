package com.csu.lab.enums;

public enum ResultEnum {
    UNKONW_ERROR(-1, "系统异常"),

    SUCCESS(0, "成功"),

    ACCOUNT_OR_PASSWORD_ERROR(1,"账号或密码错误"),

    // 保存导师信息时的错误
    TUTOR_EXIST(1, "Tutor Exist."),
    SAVE_FAILURE(2, "Failure."),

    // 修改导师信息时的错误
    UPDATE_FAILURE(1, "Failure."),

    // 查询导师信息时的错误
    TUTOR_NO_FOUND(1, "Tutor No Found."),

    // 删除导师信息时的错误
    DELETE_FAILURE(1, "Failure.")

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
