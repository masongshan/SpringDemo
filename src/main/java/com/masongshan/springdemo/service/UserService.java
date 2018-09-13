package com.masongshan.springdemo.service;

import com.masongshan.springdemo.entity.User;

import java.util.Map;

public interface UserService {
    /**
     * 获得用户
     *
     * @return
     */
    User getUser();

    /**
     * 获得所有依赖
     *
     * @return
     */
    Map<String, Object> getAllDependencies();
}
