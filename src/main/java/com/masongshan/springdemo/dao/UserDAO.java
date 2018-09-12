package com.masongshan.springdemo.dao;

import com.masongshan.springdemo.entity.User;

public class UserDAO {
    public User getUser() {
        return new User(1L, "username", "password", "MALE", "1@a.com");
    }
}
