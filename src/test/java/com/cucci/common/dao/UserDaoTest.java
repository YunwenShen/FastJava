package com.cucci.common.dao;

import com.cucci.common.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Resource
    private IUserDao userDao;

    @Test
    public void test1() {
        User user = new User();
        user.setAge(11);
        user.setHeadUrl("www.baidu.com");
        user.setName("闰土");
        user.setPassword("123456");
        userDao.insert(user);
    }

    @Test
    public void test2() {
        List<User> users = userDao.selectList(null);
        System.out.println(users);
    }


}