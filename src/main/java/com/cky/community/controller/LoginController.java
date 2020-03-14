package com.cky.community.controller;

import com.cky.community.entity.User;
import com.cky.community.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 接受用户名和密码并验证，若验证成功，设置session
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    public String login(String userName, String password,
                        HttpSession session) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "/login";
        }
        User user = userService.login(userName, password);
        if (user != null) {
            session.setAttribute("user", user);
            //session过期时间设置为7200秒 即两小时
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/";
        } else {
            session.setAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //清除session
        request.getSession().removeAttribute("user");
        //清除cookie
        Cookie[] cookies=request.getCookies();
        if (cookies!=null && cookies.length!=0){
            try{
                for(int i=0;i<cookies.length;i++){
                    Cookie cookie = new Cookie("cookie",null);
                    cookie.setMaxAge(0);
                    //根据你创建cookie的路径进行填写
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }catch(Exception ex){
                System.out.println("清理cookie异常");
            }
        }
        return "redirect:/";
    }
}

