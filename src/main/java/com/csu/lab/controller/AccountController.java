package com.csu.lab.controller;

import com.csu.lab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.lang.reflect.Method;

@RequestMapping("/server/account")
@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        如果为空则返回异常
//        if (StringUtils.isEmptyOrWhitespace(usernew.getPassword()) || StringUtils.isEmptyOrWhitespace(usernew.getUsername())) {
//            throw new CustomExceptionOne(CustomError.USER_LOGIN_FAIL);
//        }
//
//        Usernew usernew1 = usernewServices.validateLogin(usernew.getUsername(), usernew.getPassword());
//        httpServletRequest.getSession().setAttribute(CustomConst.IS_LOGIN, true);

        return "login";
    }


    @RequestMapping("/addAccount")
    public Object addAccount(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("privileges") Integer privileges) {


        return null;
    }

    @RequestMapping("/deleteAccount")
    public Object deleteAccount(@RequestParam("aid") Integer aid) {
        return null;
    }

    @RequestMapping("accountList")
    public Object accountList(@RequestParam("page_index") Integer page_index, @RequestParam("page_size") Integer page_size) {
        return null;
    }
}
