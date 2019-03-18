package com.csu.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server")
public class HomeController {


    //跳转到home界面
    @RequestMapping("/home")
    public String doIndex(){
        return "server/home";
    }


}
