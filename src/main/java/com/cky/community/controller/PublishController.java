package com.cky.community.controller;

import com.cky.community.dao.QuestionMapper;
import com.cky.community.dao.UserMapper;
import com.cky.community.entity.Question;
import com.cky.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request,
                            Model model){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if (user == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }else {
            questionMapper.insert(question);
        }
        return "redirect:/";
    }
}
