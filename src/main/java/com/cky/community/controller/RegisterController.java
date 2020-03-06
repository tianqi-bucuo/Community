package com.cky.community.controller;

import com.cky.community.entity.User;
import com.cky.community.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping({"/register"})
    public String login() {
        return "register";
    }

    /**
     * 接受用户名和密码并验证，若验证成功，设置session
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "/register";
        }
        userService.userRegister(userName, password);
        session.setAttribute("successMsg","注册成功");
        //注册成功后返回登录界面
        return "redirect:/login";
    }
}
