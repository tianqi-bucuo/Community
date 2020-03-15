package com.cky.community.controller;

import com.cky.community.dao.UserMapper;
import com.cky.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ChangeController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/change")
    public String changeAvatar(){
        return "change";
    }

    @PostMapping("/change")
    public String doChangeAvatar(String userName, String password, String avatar,String originalPassword,
                                 HttpServletRequest request, HttpServletResponse response){

        if (request.getSession().getAttribute("user")==null) return "redirect:/";
        User user = (User) request.getSession().getAttribute("user");
        if (StringUtils.isEmpty(userName) && StringUtils.isEmpty(password)
                && StringUtils.isEmpty(avatar)) {
            request.getSession().setAttribute("errorMsg", "信息不能全部为空");
            return "/change";
        }else if(StringUtils.isEmpty(originalPassword) || !originalPassword.equals(user.getPassword())){
            request.getSession().setAttribute("errorMsg", "密码错误");
            return "/change";
        }else {
            if (userMapper.getUserNames().contains(userName)){
                request.getSession().setAttribute("errorMsg","用户名重复,换一个？");
                return "/change";
            }
            Integer id = user.getId();
            userMapper.change(userName,password,avatar ,id);
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
                    request.getSession().setAttribute("errorMsg", "修改信息出错");
                }
            }
            return "redirect:login";
        }
    }
}
