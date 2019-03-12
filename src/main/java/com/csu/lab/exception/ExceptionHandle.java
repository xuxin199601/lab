package com.csu.lab.exception;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    public static final String ERROR_VIEW = "error";

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Message handle(Exception e) {
        if (e instanceof ResearcherException) {
            ResearcherException accountException = (ResearcherException) e;
            return Message.fail(accountException.getCode(), accountException.getMessage());
        } else if (e instanceof AccountException) {
            AccountException accountException = (AccountException) e;
            return Message.fail(accountException.getCode(), accountException.getMessage());
        }
        logger.error("【系统异常】{}", e.getMessage());
        return Message.fail(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
    }

//    @ExceptionHandler(value = AccountException.class)
//    @ResponseBody
//    public Message AccountExceptionHandle(AccountException e){
//
//    }

}
