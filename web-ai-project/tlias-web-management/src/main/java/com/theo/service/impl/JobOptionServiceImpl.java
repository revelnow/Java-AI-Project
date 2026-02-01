package com.theo.service.impl;

import com.theo.mapper.EmpMapper;
import com.theo.mapper.StuMapper;
import com.theo.pojo.JobOption;
import com.theo.pojo.StuOption;
import com.theo.service.JobOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobOptionServiceImpl implements JobOptionService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StuMapper stuMapper;

    /**
     * 统计职位和数据
     */
    @Override
    public JobOption JobOption() {
        List<Map<String, Object>> list = empMapper.getJobOptions();
        List<Object> joblist = list.stream().map(data -> data.get("pos")).toList();
        List<Object> datalist = list.stream().map(data -> data.get("num")).toList();

        return new JobOption(joblist, datalist);
    }

    /**
     * 统计性别和数据
     */
    @Override
    public List<Map<String, Object>> GenderOption() {
//        List<Map<String, Object>> genderOptions = empMapper.getGenderOptions();
        return empMapper.getGenderOptions();
    }

    /**
     * 统计学员学历
     */
    @Override
    public List<Map<String, Integer>> studentDegreeData() {
        return stuMapper.getStudentDegreeData();
    }

    /**
     * 统计班级人数
     */
    @Override
    public StuOption studentCountData() {
        List<Map<String, Object>> list = stuMapper.getStudentCountData();
        List<Object> clazzList = list.stream().map(data -> data.get("clazzList")).toList();
        List<Object> dataList = list.stream().map(data -> data.get("dataList")).toList();

        return new StuOption(clazzList, dataList);
    }
}
