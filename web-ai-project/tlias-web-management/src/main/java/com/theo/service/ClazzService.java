package com.theo.service;

import com.theo.pojo.Clazz;
import com.theo.pojo.ClazzQueryParam;
import com.theo.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> pageResult(ClazzQueryParam clazzQueryParam);

    void deleteClazz(Integer id);

    void addClazz(Clazz clazz);

    Clazz getClassById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getAllClazzs();
}
