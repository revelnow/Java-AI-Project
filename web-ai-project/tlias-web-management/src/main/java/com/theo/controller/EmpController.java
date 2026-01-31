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
import java.util.List;

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

    /**
     *删除员工信息
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 查询回显员工信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("查询员工{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 根据id修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
