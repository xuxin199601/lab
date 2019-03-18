package com.csu.lab.exception;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.pojo.Laboratory;
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
            ResearcherException researcherException = (ResearcherException) e;
            logger.error("【请求失败】{}", e.getMessage());
            return Message.fail(researcherException.getCode(), researcherException.getMessage());
        } else if (e instanceof AccountException) {
            AccountException accountException = (AccountException) e;
            logger.error("【请求失败】{}", e.getMessage());
            return Message.fail(accountException.getCode(), accountException.getMessage());
        } else if (e instanceof ThesisException) {
            ThesisException thesisException = (ThesisException) e;
            logger.error("【请求失败】{}", e.getMessage());
            return Message.fail(thesisException.getCode(), thesisException.getMessage());
        } else if (e instanceof ProjectException) {
            ProjectException projectException = (ProjectException) e;
            logger.error("【请求失败】{}", e.getMessage());
            return Message.fail(projectException.getCode(), projectException.getMessage());
        } else if (e instanceof ActivityException) {
            ActivityException activityException = (ActivityException) e;
            logger.error("【请求失败】{}", e.getMessage());
            return Message.fail(activityException.getCode(), activityException.getMessage());
        } else if (e instanceof DirectionException) {
            DirectionException directionException = (DirectionException) e;
            logger.error("【请求失败】{}", e.getMessage());
            return Message.fail(directionException.getCode(), directionException.getMessage());
        } else if (e instanceof LaboratoryException) {
            LaboratoryException laboratoryException = (LaboratoryException) e;
            logger.error("【请求失败】{}", e.getMessage());
            return Message.fail(laboratoryException.getCode(), laboratoryException.getMessage());
        }
        logger.error("【系统异常】{}", e.getMessage());
        return Message.fail(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
    }

}
