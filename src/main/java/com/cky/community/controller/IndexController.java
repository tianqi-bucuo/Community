package com.cky.community.controller;
import com.cky.community.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        return "index";
    }
}
