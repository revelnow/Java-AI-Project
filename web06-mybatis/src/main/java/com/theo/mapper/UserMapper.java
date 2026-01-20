package com.theo.mapper;

import com.theo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 表示该接口是一个mybatis的mapper接口, Mybatis会自动扫描该接口, 为接口生成实现类对象(代理), 并将对象交给Spring管理 --> bean
public interface UserMapper {

    /**
     * 查询所有用户
     */
    @Select("select id, username, password, name, age from user")
    public List<User> findAll();

    /**
     * 根据id删除用户 ---> #{...} ----> ? 预编译SQL
     * public Integer deleteById(Integer id); ------> 返回值表示DML语句执行影响的行数
     */
    @Delete("delete from user where id = #{id}")
    public void deleteById(Integer id);

    /**
     * 新增用户 ---> 传递的参数值是一个对象  #{对象的属性名}
     */
    @Insert("insert into user(username, password, name, age) values (#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    /**
     * 修改用户
     */
    @Update("update user set username = #{username}, password = #{password}, name = #{name}, age = #{age} where id = #{id}")
    public void update(User user);

    /**
     * 根据 用户名和密码 查询用户
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    public List<User> getByUsernameAndPassword(String username , String password);
    //public List<User> getByUsernameAndPassword(@Param("username") String username , @Param("password") String password);

    /**
     * 根据ID查询用户
     */
    public User getById(Integer id);
}
