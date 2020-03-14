package com.cky.community.controller;

import com.cky.community.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AvatarController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/changeAvatar")
    public String changeAvatar(){
        return "/changeAvatar";
    }

    @PostMapping("/changeAvatar")
    public void doChangeAvatar(String avatar){
        if (avatar==null || !avatar.startsWith("http")){
            return;
        }else {
            userMapper.changeAvatar(avatar);
        }
    }
}
