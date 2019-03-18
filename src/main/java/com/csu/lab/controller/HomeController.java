package com.csu.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    //跳转到home界面
    @RequestMapping("/server/home")
    public String doIndex(){
        return "server/home";
    }

    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */

}
