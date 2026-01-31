package com.theo.service;

import com.theo.pojo.Emp;
import com.theo.pojo.EmpQueryParam;
import com.theo.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
    /**
     * 分页查询员工信息
     */

   PageResult<Emp> page(EmpQueryParam empQueryParam);

    void add(Emp emp);

    void delete(List<Integer> ids);

    /**
     * 根据ID查询员工信息
     * @param id
     */
    Emp getById(Integer id);

    void update(Emp emp);

}
