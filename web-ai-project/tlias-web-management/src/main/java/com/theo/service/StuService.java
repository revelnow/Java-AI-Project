package com.theo.service;

import com.theo.pojo.PageResult;
import com.theo.pojo.StuQueryParam;
import com.theo.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StuService {
    PageResult<Student> page(StuQueryParam stuQueryParam);

    void delete(List<Integer> ids);

    void add(Student student);

    Student getStuById(Integer id);

    void update(Student student);

    void violationStatus(Integer id, Integer score);


}
