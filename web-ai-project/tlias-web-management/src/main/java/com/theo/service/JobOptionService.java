package com.theo.service;

import com.theo.pojo.JobOption;
import com.theo.pojo.StuOption;

import java.util.List;
import java.util.Map;

public interface JobOptionService {
    JobOption JobOption();

    List<Map<String, Object>> GenderOption();

    List<Map<String, Integer>> studentDegreeData();

    StuOption studentCountData();

}
