package com.csu.lab.controller.clientcontroller;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Message;
import com.csu.lab.service.AccountService;
import com.csu.lab.utils.CustomUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;



/*账户管理*/

@Controller
@RequestMapping("/client/account")
public class ClientAccountController {

    String msg;

    @Autowired
    AccountService accountService;

    // 跳转到账户管理界面(该方法有问题，未完成)
    @RequestMapping("/account")
    public String accountList(@RequestParam(name = "id", required = false) String aid,
                              Model model,
                              HttpSession session) {

        //session.setAttribute("error", msg);
        msg = null;

        List<Account> list;
        if (aid != null){
            model.addAttribute("key", aid);
            list = accountService.queryByProperty("username", aid);
        }else {
            list = accountService.getAccountList();
        }

        return "client/account";
    }

}
