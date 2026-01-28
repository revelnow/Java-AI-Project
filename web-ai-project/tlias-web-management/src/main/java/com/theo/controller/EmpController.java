package com.theo.controller;

import com.theo.pojo.Emp;
import com.theo.pojo.EmpQueryParam;
import com.theo.pojo.PageResult;
import com.theo.pojo.Result;
import com.theo.service.DeptService;
import com.theo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {


    @Autowired
    private EmpService empService;

    /**
     * 分页查询员工信息
     */

    @GetMapping()
    public Result page(EmpQueryParam empQueryParam) {

        log.info("分页查询, {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    /**
     * 添加员工信息
     */
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工, {}", emp);
        empService.add(emp);
        return Result.success();
    }
}
