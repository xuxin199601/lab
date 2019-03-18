package com.csu.lab.controller.clientcontroller;


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
@RequestMapping("/client")
public class ClientLoginController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private ResearcherService researcherService;

    //跳转到登录界面
    @RequestMapping("/login")
    public String doLoginLab() {
        return "client/login";
    }


}
