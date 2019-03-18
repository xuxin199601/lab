package com.csu.lab.controller;


import com.csu.lab.pojo.Account;
import com.csu.lab.service.AccountService;
import com.csu.lab.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private ResearcherService researcherService;

    //跳转到登录界面
    @RequestMapping("/server/login")
    public String doLoginLab() {
        return "server/login";
    }


    //跳转到project
    @RequestMapping("/server/project")
    public String doProject(){
        return "server/gain/projectManage";
    }


    //登录实现
    @RequestMapping(value = "/server/doLogin",method = RequestMethod.POST)
    public String doLogin(Account account, Model model, HttpSession httpSession) {

        Account user = accountService.loginVaildata(account);

        if (user != null) {
            httpSession.setAttribute("user", user);
            return "redirect:home";
        } else {
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return "server/login";
        }
    }

}
