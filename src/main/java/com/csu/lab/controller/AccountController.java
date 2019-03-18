package com.csu.lab.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;


/**
 * 账户管理
 */
@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    //账户管理界面
    @RequestMapping("/server/account/accountList")
    public String accountList(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              Model model) {

        PageHelper.startPage(pageNum,pageSize);

        List<Account> list = accountService.getAccountList();
        PageInfo pageInfo = new PageInfo(list,10);

        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages",pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage",pageInfo.isIsLastPage());
        return "server/account/accountManage";
    }

    @RequestMapping("/server/account/addAccount")
    @ResponseBody
    public Message addAccount(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("privileges") Integer privileges) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setPrivileges(privileges);
        account.setCreateTime(CustomUtils.getCustomTime(Calendar.getInstance().getTime()));
        accountService.addAccount(account);
        return Message.success().add(ResultEnum.SUCCESS.getMsg());
    }

    @RequestMapping("/server/account/deleteAccount")
    @ResponseBody
    public Message deleteAccount(@RequestParam("aid") Integer aid) {

        if (accountService.deleteAccount(aid) == 1) {
            return Message.success().add(ResultEnum.SUCCESS);
        } else {
            return Message.fail(ResultEnum.ACCOUNT_DELETE_FAILURE);
        }
    }

    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */
}
