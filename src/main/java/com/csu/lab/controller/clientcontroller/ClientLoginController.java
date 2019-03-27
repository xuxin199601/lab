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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
    public String doLogin(Account account, RedirectAttributesModelMap modelMap, HttpSession session) {

        Account user = accountService.loginVaildata(account);

        if (user != null) {

            //通过aid查询用户，获得个人信息
            List<Researcher> researcherList = researcherService.queryByProperty("aid",user.getAid());
            session.setAttribute("researcher",researcherList.get(0));

            return "redirect:/client/index";
        } else {
            //注意Model属性会随着重定向而被销毁，所以这里直接使用RedirectAttributesModelMap对象的addFlashAttribute方法来添加Model属性。
            modelMap.addFlashAttribute("error", "用户名或密码错误，请重新登录！");
            return "redirect:/client/login";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session,Model model){
        session.setAttribute("researcher", null);
        return "redirect:/client/index";
    }

}
