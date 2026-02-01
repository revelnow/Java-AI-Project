package com.theo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.theo.mapper.StuMapper;
import com.theo.pojo.PageResult;
import com.theo.pojo.StuQueryParam;
import com.theo.pojo.Student;
import com.theo.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    /**
     * 分页查询学生信息
     */
    @Override
    public PageResult<Student> page(StuQueryParam stuQueryParam) {
        //1.开启分页
        PageHelper.startPage(stuQueryParam.getPage(), stuQueryParam.getPageSize());

        //2.执行查询
        List<Student> studentList = stuMapper.list(stuQueryParam);

        //3.封装并返回结果
        Page<Student> p= (Page<Student>) studentList;
        PageResult<Student> pageResult = new PageResult<>(p.getTotal(), p.getResult());
        return pageResult;

    }

    /**
     * 批量删除学生信息
     */
    @Override
    public void delete(List<Integer> ids) {
        stuMapper.deleteByIds(ids);
    }

    /**
     * 添加学生信息
     */
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.add(student);
    }

    /**
     * 根据id查询学生信息
     */
    @Override
    public Student getStuById(Integer id) {
        return stuMapper.getStuById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.update(student);
    }

    @Override
    public void violationStatus(Integer id, Integer score) {
        stuMapper.violationStatus(id, score);
    }

}
