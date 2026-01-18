package com.theo.dao;

import org.springframework.stereotype.Component;

import java.util.List;


public interface UserDao {
    /**
     * 查询所有用户
     */
    public List<String> list();
}
