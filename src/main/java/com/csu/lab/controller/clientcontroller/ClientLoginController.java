package com.csu.lab.controller.clientcontroller;


import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.AccountService;
import com.csu.lab.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientLoginController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    ResearcherService researcherService;


    //跳转到登录界面
    @RequestMapping("/login")
    public String doLoginLab() {
        return "client/account/login";
    }

    //登录实现
    @RequestMapping(value = "/loginCheck",method = RequestMethod.POST)
    public String doLogin(Account account, Model model, HttpSession session) {
        //boolean sessionState = true;
        Account user = accountService.loginVaildata(account);

        if (user != null) {
            session.setAttribute("user", user);
            //sessionState = false;

            //通过aid查询用户，获得个人信息
            List<Researcher> researcherList = researcherService.queryByProperty("aid",user.getAid());
            model.addAttribute("researcher",researcherList.get(0));
            //model.addAttribute("sessionState", sessionState);

            //return "redirect:/client/index";
            return "client/index";
        } else {
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return "client/account/login";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session,Model model){
        boolean sessionState = false;
        session.setAttribute("user", null);
        sessionState = false;
        model.addAttribute("sessionState", sessionState);
        return "redirect:/client/index";
    }

}
