package com.theo.service.impl;

import com.theo.dao.UserDao;
import com.theo.dao.impl.UserDaoImpl;
import com.theo.pojo.User;
import com.theo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service//将当前类交给Spring管理, 创建对象放在容器中
public class UserServiceImpl implements UserService {

    @Autowired //自动装配: 应用程序在运行时, 会自动的从容器中找到该类型的对象, 并赋值给该变量
    private UserDao userDao ;

    @Override
    public List<User> list() {
        //1. 调用dao层, 获取数据
        List<String> lines = userDao.list();

        //2. 业务逻辑处理: 解析数据, 封装User对象 --> List<User>
        List<User> userList = lines.stream().map(line -> {
            String[] split = line.split(",");
            return new User(
                    Integer.parseInt(split[0]),
                    split[1],
                    split[2],
                    split[3],
                    Integer.parseInt(split[4]),
                    LocalDateTime.parse(split[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }).toList();
        return userList;
    }
}
