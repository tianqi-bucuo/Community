package com.cky.community;

import com.cky.community.dao.ArticleMapper;
import com.cky.community.dao.CommentMapper;
import com.cky.community.dao.NotificationMapper;
import com.cky.community.dao.UserMapper;
import com.cky.community.dto.CommentDto;
import com.cky.community.entity.Article;
import com.cky.community.entity.Comment;
import com.cky.community.entity.User;
import com.cky.community.service.impl.ArticleServiceImpl;
import com.cky.community.service.impl.CommentServiceImpl;
import com.cky.community.service.impl.NotificationServiceImpl;
import com.cky.community.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleServiceImpl articleService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    NotificationServiceImpl notificationService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserMapper(){
        userService.registerWithAvatar("cky","123","https://images.cnblogs.com/cnblogs_com/cky-2907183182/1521479/o_background.jpg");
    }

    @Test
    public void testUserService(){
        userService.userRegister("小地瓜","123");
    }

    @Test
    public void testArticleService(){
        List<Article> articles = articleService.findHotArticles();
        System.out.println(articles);
    }

    @Test
    public void testArticleMapper() {
        for (int i =100;i < 200;i++){
            Article article = new Article();
            article.setAuthorId(1);
            article.setContent(Integer.toString(i));
            article.setTitle(Integer.toString(i));
            articleMapper.create(article);
        }
    }


    @Test
    public void testCommentService(){
        List<CommentDto> list = commentService.getCommentList(7);
        System.out.println(list);
    }

    @Test
    public void testCommentMapper(){
        List<Comment> comments = commentMapper.findByArticleId(7);
        System.out.println(comments);
    }

    @Test
    public void testNotify(){
        int unreadCount = notificationService.unreadCount(1);
        System.out.println(unreadCount);
    }
}
