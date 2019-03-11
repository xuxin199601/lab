package com.csu.lab.exception;

import com.csu.lab.enums.ResultEnum;

public class ResearcherException extends RuntimeException {

    private Integer code;

    public ResearcherException(Integer code, String result) {
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
