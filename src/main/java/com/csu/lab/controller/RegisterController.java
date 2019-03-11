package com.csu.lab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server")
public class RegisterController {

    @RequestMapping("/register")
    public String doRegister() {
        return "register";
    }

}
