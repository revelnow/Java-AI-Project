package com.theo.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.theo.pojo.User;
import com.theo.service.UserService;
import com.theo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

///**
// * 用户信息Controller
// */
//@RestController //@Controller + @ResponseBody -----> 如果返回的是一个对象/集合 --> 转json --> 响应
//public class UserController {
//    @RequestMapping("/list")
//    public List<User> list(){
//        //1. 读取user.txt中的数据
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
//        ArrayList<String> lines = IoUtil.readUtf8Lines(in, new ArrayList<>());
//
//        //2. 业务逻辑处理: 解析数据, 封装User对象 --> List<User>
//        List<User> userList = lines.stream().map(line -> {
//            String[] split = line.split(",");
//            return new User(
//                    Integer.parseInt(split[0]),
//                    split[1],
//                    split[2],
//                    split[3],
//                    Integer.parseInt(split[4]),
//                    LocalDateTime.parse(split[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        }).toList();
//
//        //3. 响应数据
//        return userList;

/**
 * 用户信息Controller
 */
@RestController //@Controller + @ResponseBody -----> 如果返回的是一个对象/集合 --> 转json --> 响应
public class UserController {
//    @Autowired
//    private UserService userService;

    @Qualifier("userServiceImpl") // 或 "userServiceImpl2"
    private UserService userService;


    @RequestMapping("/list")
    public List<User> list(){
        List<User> userList = userService.list();

        //3. 响应数据
        return userList;
    }
}
