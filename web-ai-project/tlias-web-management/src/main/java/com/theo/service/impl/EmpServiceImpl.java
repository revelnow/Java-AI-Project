package com.theo.service.impl;

import com.github.pagehelper.Page;
import com.theo.mapper.EmpExprMapper;
import com.theo.mapper.EmpMapper;
import com.theo.pojo.Emp;
import com.theo.pojo.EmpExpr;
import com.theo.pojo.EmpQueryParam;
import com.theo.pojo.PageResult;
import com.theo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList=empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>)empList;

        //3.封装并返回结果
        PageResult<Emp> pageResult = new PageResult<>(p.getTotal(), p.getResult());
        return pageResult;
    }

    @Transactional
    @Override
    public void add(Emp emp) {
        /**
         * 添加员工基本信息
         */
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        /**
         *添加员工经历信息
         */
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合, 为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }

    }
}
