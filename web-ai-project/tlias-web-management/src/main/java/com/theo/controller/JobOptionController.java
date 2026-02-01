package com.theo.controller;
import com.theo.pojo.JobOption;
import com.theo.pojo.Result;
import com.theo.pojo.StuOption;
import com.theo.service.JobOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("report")
@RestController
public class JobOptionController {

    @Autowired
    private JobOptionService jobOptionService;

    /**
     * 统计职位和数据
     */
    @GetMapping("/empJobData")
    public Result getJobOption() {
        log.info("统计职位和数据");
        JobOption data = jobOptionService.JobOption();
        return Result.success(data);
    }

    /**
     * 统计性别和数据
     */
    @GetMapping("/empGenderData")
    public Result getGenderOption() {
        log.info("统计性别和数据");
        List<Map<String,Object>> data = jobOptionService.GenderOption();
        return Result.success(data);
    }

    /**
     * 统计学员学历
     */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("统计学员学历数据");
        List<Map<String,Integer>> data = jobOptionService.studentDegreeData();
        return Result.success(data);
    }

    /**
     * 统计班级人数
     */
    @GetMapping("/studentCountData")
    public Result studentCountData() {
        log.info("统计班级人数数据");
        StuOption data = jobOptionService.studentCountData();
        return Result.success(data);
    }

}
