package com.csu.lab.exception;

import com.csu.lab.enums.ResultEnum;

public class AccountException extends RuntimeException {
    private Integer code;

    public AccountException(Integer code, String result) {
        super(result);
        this.code = code;
    }

    public AccountException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
