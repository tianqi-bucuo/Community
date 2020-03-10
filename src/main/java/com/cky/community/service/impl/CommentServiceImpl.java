package com.cky.community.service.impl;

import com.cky.community.component.enums.CommentTypeEnum;
import com.cky.community.dao.ArticleMapper;
import com.cky.community.dao.CommentMapper;
import com.cky.community.dto.CommentDto;
import com.cky.community.entity.Article;
import com.cky.community.entity.Comment;
import com.cky.community.entity.User;
import com.cky.community.exception.ErrorCode;
import com.cky.community.exception.PageException;
import com.cky.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public void create(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new PageException(ErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getCommentType() == null || !CommentTypeEnum.isExist(comment.getCommentType())) {
            throw new PageException(ErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getCommentType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment parentComment = commentMapper.findById(comment.getParentId());
            if (parentComment==null){
                throw new PageException(ErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.create(comment);
        }else {
            //回复文章
            Article article = articleMapper.findById(comment.getParentId());
            if (article==null){
                throw new PageException(ErrorCode.ARTICLE_NOT_FOUND);
            }
            commentMapper.create(comment);
            articleMapper.incCommentCount(article.getId());
        }
    }
}
