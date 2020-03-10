package com.cky.community.controller;

import com.cky.community.dto.CommentCreateDto;
import com.cky.community.dto.ResultDto;
import com.cky.community.entity.Comment;
import com.cky.community.entity.User;
import com.cky.community.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @ResponseBody
    @PostMapping(value = "/comment")
    public Object post(@RequestBody CommentCreateDto commentCreateDto, HttpServletRequest request){

        User user = (User) request.getAttribute("user");
        if (user==null){
            return ResultDto.errorOf(2002,"请先登录");
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setCommentType(commentCreateDto.getCommentType());
        comment.setUserId(user.getId());
        commentService.create(comment);
        return ResultDto.okOf();
    }
}
