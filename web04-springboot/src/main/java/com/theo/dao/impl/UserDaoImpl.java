package com.theo.dao.impl;

import cn.hutool.core.io.IoUtil;
import com.theo.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository//将当前类交给spring管理, 声明为spring容器中bean对象
public class UserDaoImpl implements UserDao {
    @Override
    public List<String> list() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readUtf8Lines(in, new ArrayList<>());
        return lines;
    }
}
