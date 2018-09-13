package com.masongshan.springdemo.service.impl;

import com.masongshan.springdemo.dao.UserDAO;
import com.masongshan.springdemo.entity.User;
import com.masongshan.springdemo.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private Integer testIntegerAttr;
    private String testStringAttr;

    @Override
    public User getUser() {
        return userDAO.getUser();
    }

    @Override
    public Map<String, Object> getAllDependencies() {
        Map<String, Object> dependencies = new HashMap<>();
        dependencies.put("user", userDAO.getUser());
        dependencies.put("testIntegerAttr", testIntegerAttr);
        dependencies.put("testStringAttr", testStringAttr);
        return dependencies;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setTestIntegerAttr(Integer testIntegerAttr) {
        this.testIntegerAttr = testIntegerAttr;
    }

    public void setTestStringAttr(String testStringAttr) {
        this.testStringAttr = testStringAttr;
    }
}
