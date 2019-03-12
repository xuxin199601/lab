package com.csu.lab.exception;

public class AccountException extends  RuntimeException {
    private Integer code;

    public AccountException(Integer code, String result) {
        super(result);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
