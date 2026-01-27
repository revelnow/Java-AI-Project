package com.theo.service;

import com.theo.pojo.Emp;
import com.theo.pojo.EmpQueryParam;
import com.theo.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


public interface EmpService {
    /**
     * 分页查询员工信息
     */

   PageResult<Emp> page(EmpQueryParam empQueryParam);
}
