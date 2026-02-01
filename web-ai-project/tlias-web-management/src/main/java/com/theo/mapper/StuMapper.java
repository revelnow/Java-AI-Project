package com.theo.mapper;

import com.theo.pojo.StuOption;
import com.theo.pojo.StuQueryParam;
import com.theo.pojo.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {
    /**
     * 分页查询学生信息
     */
    List<Student> list(StuQueryParam stuQueryParam);

    /**
     * 根据id批量删除学生信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 添加学生信息
     */
    void add(Student student);

    /**
     * 根据id查询学生信息
     */
    Student getStuById(Integer id);

    /**
     * 更新学生信息
     */
    void update(Student student);

    /**
     * 更新学生违纪状态和分数
     */
    @Update("update student set violation_score=#{score}, update_time=now() where id=#{id}")
    void violationStatus(Integer id, Integer score);

    /**
     * 获取学生学历及相关数据
     */
    @MapKey("degree")
    List<Map<String, Integer>> getStudentDegreeData();

    /**
     * 获取班级学生数量及相关数据
     */
    @MapKey("classList")
    List<Map<String, Object>> getStudentCountData();
}
