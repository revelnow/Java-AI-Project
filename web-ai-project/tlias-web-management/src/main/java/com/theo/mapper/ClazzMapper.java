package com.theo.mapper;

import com.theo.pojo.Clazz;
import com.theo.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 分页查询班级信息
     */
    List<Clazz> getClazzQuery(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级
     */
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    /**
     * 添加班级
     */
    void addClazz(Clazz clazz);

    /**
     * 根据id查询班级信息
     */
    @Select("select id, name, room,begin_date, end_date, create_time, update_time ,master_id ,subject from clazz where id=#{id}")
    Clazz getClassById(Integer id);

    /**
     * 更新班级信息
     */
    void updateClazz(Clazz clazz);

    /**
     * 查询所有班级信息
     */
    @Select("select id, name, room,begin_date, end_date, create_time, update_time ,master_id ,subject from clazz")
    List<Clazz> getAllClazzs();
}
