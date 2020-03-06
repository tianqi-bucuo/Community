package com.cky.community;

import com.cky.community.dao.ArticleMapper;
import com.cky.community.dao.UserMapper;
import com.cky.community.entity.Article;
import com.cky.community.entity.User;
import com.cky.community.service.impl.ArticleServiceImpl;
import com.cky.community.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserMapper(){
        User user = userMapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void testUserService(){
        userService.userRegister("小地瓜","123");
    }

    @Test
    public void testArticleService(){
        Article article = new Article();
        article.setAuthorId(1);
        article.setContent("1");
        article.setTag("1");
        article.setTitle("1");
        articleService.create(article);
    }
}
