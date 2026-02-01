package com.theo.controller;

import com.theo.pojo.PageResult;
import com.theo.pojo.Result;
import com.theo.pojo.StuQueryParam;
import com.theo.pojo.Student;
import com.theo.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/students")
@RestController
public class StuController {

    //注入StuService
    @Autowired
    private StuService stuService;

    /**
     * 分页查询学生信息
     */
    @GetMapping
    public Result page(StuQueryParam stuQueryParam) {
        log.info("分页查询学生信息");
        PageResult<Student> pageResult = stuService.page(stuQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 批量删除学生信息
     */
    @DeleteMapping("{ids}")
    public Result delete( @PathVariable List<Integer> ids) {
        log.info("批量删除学生信息{}", ids);
        stuService.delete(ids);
        return Result.success();
    }

    /**
     * 添加学生信息
     */
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学生信息{}", student);
        //调用service添加学生信息的方法
        stuService.add(student);
        return Result.success();
    }

    /**
     * 查询回显学生信息
     */
    @GetMapping("/{id}")
    public Result getStuById(@PathVariable Integer id) {
        log.info("执行了根据id查询学生信息用于回显操作, 查询学生id为: {}", id);
        //调用service查询学生信息的方法
        Student data = stuService.getStuById(id);
        return Result.success(data);
    }

    /**
     * 更新学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("更新学生信息{}", student);
        //调用service更新学生信息的方法
        stuService.update(student);
        return Result.success();
    }

    /**
     * 修改违纪状态
     */
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolationStatus(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("修改违纪状态, 学生ID: {}, 违纪分数: {}", id, score);
        stuService.violationStatus(id, score);
        return Result.success();
    }



}
