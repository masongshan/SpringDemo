package com.masongshan.springdemo.service.impl;

import com.masongshan.springdemo.dao.UserDAO;
import com.masongshan.springdemo.entity.User;
import com.masongshan.springdemo.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Override
    public User getUser() {
        return userDAO.getUser();
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
