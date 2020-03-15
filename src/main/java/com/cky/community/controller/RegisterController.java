package com.cky.community.controller;

import com.cky.community.dao.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

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
    public String register(String userName, String password, @RequestParam(name = "avatar", required = false) String avatar,
                           HttpSession session
                           ) {

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "/register";
        }else if (userMapper.getUserNames().contains(userName)){
            session.setAttribute("errorMsg","用户名重复,换一个？");
            return "/register";
        }
        if (avatar==null|| !avatar.startsWith("http")){
            userService.userRegister(userName, password);
        }else {
            session.removeAttribute("errorMsg");
            userService.registerWithAvatar(userName,password,avatar);
        }
        //注册成功后返回登录界面
        return "redirect:/login";
    }
}
