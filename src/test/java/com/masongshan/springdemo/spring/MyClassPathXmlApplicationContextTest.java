package com.masongshan.springdemo.spring;

import com.masongshan.springdemo.entity.User;
import com.masongshan.springdemo.service.UserService;
import org.junit.Assert;
import org.junit.Test;

public class MyClassPathXmlApplicationContextTest {
    private MyClassPathXmlApplicationContext context;

    @org.junit.Before
    public void setUp() throws Exception {
        context = new MyClassPathXmlApplicationContext("applicationContext.xml");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * 容器管理bean测试
     *
     * @throws Exception
     */
    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(context.getInstance("userDAO"));
    }

    /**
     * 依赖注入测试
     *
     * @throws Exception
     */
    @Test
    public void testDependencyInjection() throws Exception {
        UserService userService = (UserService) context.getInstance("userService");
        User user = userService.getUser();
        Assert.assertNotNull(user);
    }
}