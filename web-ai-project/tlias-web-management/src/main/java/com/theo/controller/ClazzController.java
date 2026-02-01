package com.theo.controller;

import com.theo.pojo.Clazz;
import com.theo.pojo.ClazzQueryParam;
import com.theo.pojo.PageResult;
import com.theo.pojo.Result;
import com.theo.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 基本查询
     */
    @GetMapping
    public Result getClazz(ClazzQueryParam clazzQueryParam) {
        log.info("执行了班级的基本查询操作");
        PageResult<Clazz> pageResult = clazzService.pageResult(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除班级
     */
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id) {
        log.info("执行了班级的删除操作, 删除班级id为: {}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }

    /**
     * 添加班级
     */
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("执行了班级的添加操作, 添加班级信息为: {}", clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 根据id查询班级信息用于回显
     */
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id) {
        log.info("执行了根据id查询班级信息用于回显操作, 查询班级id为: {}", id);
        Clazz data = clazzService.getClassById(id);
        return Result.success(data);
    }
    /**
     * 修改班级信息
     */
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("执行了班级的修改操作, 修改班级信息为: {}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }
    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result getAllClazzs() {
        log.info("执行了查询所有班级信息操作");
        List<Clazz> clazzList=clazzService.getAllClazzs();
        return Result.success(clazzList);
    }
}
