package com.csu.lab.controller;


import com.csu.lab.customConst.CustomConstant;
import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.AccountException;
import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.AccountService;
import com.csu.lab.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
//            throw new AccountException(ResultEnum.ACCOUNT_OR_PASSWORD_IS_SPACE.getCode(), ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getMsg());
            return "login";
        }
        account.setPassword(account.getPassword());
        Account newAccount = accountService.loginVaildata(account);
        httpServletRequest.getSession().setAttribute(CustomConstant.IS_LOGIN, true);

        // 登陆成功，查询信息并跳转到管理页面
        List<Researcher> researcherList = researcherService.getResearcherList();
        modelMap.addAttribute("researchers", researcherList);
        return "researcher_management";
    }

    @RequestMapping("/login")
    public String doLoginLab() {
        return "login";
    }


}
