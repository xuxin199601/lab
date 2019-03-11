package com.csu.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server")
public class FormManageController {

    @RequestMapping("/form_management")
    public String getFormManagement() {
        return "form_management";
    }

    @RequestMapping("/form_one")
    public String reviewForm() {
        return "form_one";
    }
}
