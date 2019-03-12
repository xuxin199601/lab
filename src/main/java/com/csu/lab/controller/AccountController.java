package com.csu.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/server/account")
@Controller
public class AccountController {
    @RequestMapping("/addAccount")
    public Object addAccount(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("privileges") Integer privileges) {


        return null;
    }

    @RequestMapping("/deleteAccount")
    public Object deleteAccount(@RequestParam("aid") Integer aid) {
        return null;
    }

    @RequestMapping("accountList")
    public Object accountList(@RequestParam("page_index") Integer page_index, @RequestParam("page_size") Integer page_size) {
        return null;
    }
}
