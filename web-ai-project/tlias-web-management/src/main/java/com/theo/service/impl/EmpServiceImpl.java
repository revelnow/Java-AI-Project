package com.theo.service.impl;

import com.github.pagehelper.Page;
import com.theo.mapper.EmpMapper;
import com.theo.pojo.Emp;
import com.theo.pojo.EmpQueryParam;
import com.theo.pojo.PageResult;
import com.theo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empmapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList=empmapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>)empList;

        //3.封装并返回结果
        PageResult<Emp> pageResult = new PageResult<>(p.getTotal(), p.getResult());
        return pageResult;
    }
}
