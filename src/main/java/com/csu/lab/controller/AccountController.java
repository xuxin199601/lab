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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpHandler;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * 账户管理
 */
@Controller
@RequestMapping("/server/account")
public class AccountController {

    String msg;

    @Autowired
    AccountService accountService;

    // 跳转到账户管理界面
    @RequestMapping("/accountList")
    public String accountList(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "value", required = false) String value,
                              Model model,
                              HttpSession session) {

        session.setAttribute("error", msg);
        msg = null;

        PageHelper.startPage(pageNum,pageSize);

        List<Account> list;
        if (value != null){
            model.addAttribute("key", value);
            list = accountService.queryByProperty("username", value);
        }else {
            list = accountService.getAccountList();
        }

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

    // 跳转到新增账户页面
    @RequestMapping("/addAccount")
    public String toAddPage() {
        return "server/account/addAccount";
    }

    // 跳转到修改账户页面
    @RequestMapping("/editAccount")
    public String toEditPage(@RequestParam("id")Integer aid,
                             Model model) {
        Account account = accountService.queryAccountById(aid);
        model.addAttribute("account", account);

        return "server/account/addAccount";
    }

    // 保存添加的账户信息，跳转到账户管理界面
    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String saveTutor(Account account) {
        int result = accountService.addAccount(account);
        if (result == 1){
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return "redirect:/server/account/accountList";
    }

    // 保存修改的账户信息，跳转到账户管理界面
    @RequestMapping(value = "/saveAccount", method = RequestMethod.PUT)
    public String saveEditTutor(Account account) {
        int result = accountService.updateAccount(account);
        if (result == 1){
            msg = "修改成功";
        } else {
            msg = "修改失败";
        }
        return "redirect:/server/account/accountList";
    }

    // 删除账户信息，跳转到账户管理界面
    @RequestMapping("/deleteAccount")
    public String delTutor(@RequestParam("id")Integer rid) {
        int result = accountService.deleteAccount(rid);
        if (result == 1){
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }
        return "redirect:/server/account/accountList";
    }

}
