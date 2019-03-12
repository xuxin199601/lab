package com.csu.lab.pojo;

import com.csu.lab.enums.ResultEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * json的通用返回类
 */
public class Message {
    // 200 成功 404 失败
    private int code;
    // 服务器要返回给浏览器的数据
    private Object result;

    public static Message success() {
        Message message = new Message();
        message.setCode(ResultEnum.SUCCESS.getCode());
        return message;
    }

    public static Message fail(Integer code, String result) {
        Message message = new Message();
        message.setCode(code);
        message.setResult(result);
        return message;
    }

    public static Message fail(ResultEnum resultEnum) {
        Message message = new Message();
        message.setCode(resultEnum.getCode());
        message.setResult(resultEnum.getMsg());
        return message;
    }

    public Message add(Object data) {
//        this.data.put("result", value);
        this.setResult(data);
        return this;
    }

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
