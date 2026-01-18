package com.theo.service;

import com.theo.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     */
    public List<User> list();
}
