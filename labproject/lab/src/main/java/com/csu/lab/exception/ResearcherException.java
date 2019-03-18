package com.csu.lab.exception;

import com.csu.lab.enums.ResultEnum;

public class ResearcherException extends RuntimeException {

    private Integer code;

    public ResearcherException(ResultEnum resultEnum) {
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
