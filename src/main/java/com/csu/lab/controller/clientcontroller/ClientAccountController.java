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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;



/*账户管理*/

@Controller
@RequestMapping("/client/account")
public class ClientAccountController {

    @Autowired
    AccountService accountService;


}
