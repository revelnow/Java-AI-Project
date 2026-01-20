package com.theo;

import com.theo.mapper.UserMapper;
import com.theo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest //运行该测试类时，自动加载springboot的环境。
class Web06MybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        List<User> userList = userMapper.findAll();
        userList.stream().forEach(user -> System.out.println(user));
    }

    /**
     * 测试根据ID删除用户
     */
    @Test
    public void testDeleteById(){
        //Integer i = userMapper.deleteById(4);
        //System.out.println(i);
        userMapper.deleteById(4);
    }

    /**
     * 测试添加用户
     */
    @Test
    public void testInsert(){
        User user = new User(null, "admin66", "123456", "管理员", 18);
        userMapper.insert(user);
    }

    /**
     * 测试修改用户
     */
    @Test
    public void testUpdate(){
        User user = new User(6, "songjiang", "12345678", "及时雨", 48);
        userMapper.update(user);
    }

    /**
     * 测试根据用户名和密码查询用户
     */
    @Test
    public void testGetByUsernameAndPassword(){
        List<User> userList = userMapper.getByUsernameAndPassword("songjiang", "12345678");
        userList.stream().forEach(user -> System.out.println(user));
    }

    /**
     * 测试根据ID查询用户
     */
    @Test
    public void testGetById(){
        User user = userMapper.getById(6);
        System.out.println(user);
    }
}
