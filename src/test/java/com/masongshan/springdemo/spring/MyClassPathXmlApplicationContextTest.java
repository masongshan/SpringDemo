package com.masongshan.springdemo.spring;

import com.masongshan.springdemo.entity.User;
import com.masongshan.springdemo.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class MyClassPathXmlApplicationContextTest {
    private MyClassPathXmlApplicationContext context;
    private UserService userService;

    @org.junit.Before
    public void setUp() throws Exception {
        context = new MyClassPathXmlApplicationContext("applicationContext.xml");
        userService = (UserService) context.getInstance("userService");
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
        User user = userService.getUser();
        Assert.assertNotNull(user);
    }

    @Test
    public void testBasicTypeDependeccyInjection() throws Exception {
        Map<String, Object> dependencies = userService.getAllDependencies();
        Assert.assertNotNull(dependencies.get("user"));
        Assert.assertEquals(1, dependencies.get("testIntegerAttr"));
        Assert.assertEquals("testString", dependencies.get("testStringAttr"));
    }
}