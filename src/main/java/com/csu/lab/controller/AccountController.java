package com.csu.lab.controller;

import com.csu.lab.customConst.CustomConstant;
import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.AccountException;
import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Message;
import com.csu.lab.service.AccountService;
import com.csu.lab.utils.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/server/account")
@Controller
public class AccountController {
    @Autowired
    AccountService accountService;


    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Message login(@RequestParam("username") String username, @RequestParam("password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        如果为空则返回异常
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        if (StringUtils.isEmptyOrWhitespace(account.getPassword()) || StringUtils.isEmptyOrWhitespace(account.getUsername())) {
            throw new AccountException(ResultEnum.ACCOUNT_OR_PASSWORD_IS_SPACE.getCode(), ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getMsg());
        }
        account.setPassword(account.getPassword());
        Account newAccount = accountService.loginVaildata(account);
        httpServletRequest.getSession().setAttribute(CustomConstant.IS_LOGIN, true);

        return Message.success().add(newAccount);
    }


    @RequestMapping("/addAccount")
    @ResponseBody
//    @Transactional(propagation = Propagation.SUPPORTS)
    public Message addAccount(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("privileges") Integer privileges) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setPrivileges(privileges);
        account.setCreateTime(CustomUtils.getCustomTime(Calendar.getInstance().getTime()));
        accountService.addAccount(account);
        return Message.success().add(ResultEnum.SUCCESS.getMsg());
    }

    @RequestMapping("/deleteAccount")
    @ResponseBody
//    @Transactional(propagation = Propagation.REQUIRED)
    public Message deleteAccount(@RequestParam("aid") Integer aid) {

        if(accountService.deleteAccount(aid)==1){
            return Message.success().add(ResultEnum.SUCCESS);
        }else{
            return Message.fail(ResultEnum.ACCOUNT_DELETE_FAILURE);
        }
//        return null;
    }

    @RequestMapping("accountList")
    @ResponseBody
//    @Transactional(propagation = Propagation.SUPPORTS)
    public Message accountList(@RequestParam("page_index") Integer page_index, @RequestParam("page_size") Integer page_size) {

        List<Account> accountList = accountService.getAccountList(page_index,page_size);
        return Message.success().add(accountList);
    }
}
