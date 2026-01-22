package com.theo.service;

import com.theo.pojo.Dept;

import java.util.List;

public interface DeptService {


    void deleteById(Integer id);

    List<Dept> findAll();

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
