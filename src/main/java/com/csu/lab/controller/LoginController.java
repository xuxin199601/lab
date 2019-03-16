package com.csu.lab.controller;


import com.csu.lab.customConst.CustomConstant;
import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.AccountService;
import com.csu.lab.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/server")
public class LoginController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private ResearcherService researcherService;

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          ModelMap modelMap) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        if (StringUtils.isEmptyOrWhitespace(account.getPassword()) || StringUtils.isEmptyOrWhitespace(account.getUsername())) {
            modelMap.addAttribute("msg", "用户名密码错误");
            return "index";
        }
        account.setPassword(account.getPassword());
        Account newAccount = accountService.loginVaildata(account);
        httpServletRequest.getSession().setAttribute(CustomConstant.IS_LOGIN, true);

        // 登陆成功，跳转到管理页面
        List<Researcher> researcherList = researcherService.queryResearcherListPaged(0, 1, 10);
        modelMap.addAttribute("researchers", researcherList);
        return "tutor_list";
    }

    @RequestMapping("/index")
    public String doLoginLab() {
        return "login";
    }


}
