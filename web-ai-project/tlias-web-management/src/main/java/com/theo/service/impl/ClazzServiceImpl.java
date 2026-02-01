package com.theo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.theo.mapper.ClazzMapper;
import com.theo.pojo.Clazz;
import com.theo.pojo.ClazzQueryParam;
import com.theo.pojo.PageResult;
import com.theo.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService{

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> pageResult(ClazzQueryParam clazzQueryParam) {
        //开启分页
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        //将查询结果封装到PageResult对象中并返回
        List<Clazz> page = clazzMapper.getClazzQuery(clazzQueryParam);
        for (Clazz clazz : page) {
            //注意：班级状态，显示为：未开班、已结课、在读中 这三种。
            //如果：
            //  - 当前时间 > 结课时间：状态未 已结课。
            //  - 当前时间 < 开课时间：状态未 未开班。
            //  - 否则，就是 在读中。
            LocalDate now = LocalDate.now();
            if (now.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else if (now.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }

        Page<Clazz> p = (Page<Clazz>) page;
        PageResult<Clazz> pageResult = new PageResult<>(p.getTotal(), p.getResult());
        return pageResult;

    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    /**
     * 根据id查询班级信息
     */
    @Override
    public Clazz getClassById(Integer id) {
        Clazz date =clazzMapper.getClassById(id);
        return date;
    }

    /**
     * 修改班级信息
     */
    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public List<Clazz> getAllClazzs() {
        List<Clazz> clazzList=clazzMapper.getAllClazzs();
        return clazzList;
    }
}
