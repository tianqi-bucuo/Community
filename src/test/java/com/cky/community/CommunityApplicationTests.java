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

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserMapper(){
        for (int i = 0;i < 20;i++){
            userMapper.create(Integer.toString(i),Integer.toString(i));
        }
    }

    @Test
    public void testUserService(){
        userService.userRegister("小地瓜","123");
    }

    @Test
    public void testArticleService(){
        List<Article> articles = articleMapper.getArticleList(1,5);
        System.out.println(articles);
    }

    @Test
    public void testArticleMapper() {
        int count = articleMapper.count();
        System.out.println(count);
    }
}
