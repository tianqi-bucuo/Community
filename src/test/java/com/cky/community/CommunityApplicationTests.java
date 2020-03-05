package com.cky.community;

import com.cky.community.dao.UserMapper;
import com.cky.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserInsert(){
        User user = new User();
        user.setAccountId("1");
        user.setCreateTime(new Date());
        user.setName("周润发");
        user.setToken("123");

        userMapper.insert(user);
    }

    @Test
    public void testUserSelect(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

}
