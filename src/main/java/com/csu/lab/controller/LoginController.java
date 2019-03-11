package com.csu.lab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/server")
public class LoginController {

    @RequestMapping("/login")
    public String doLogin() {
        return "login";
    }

}
