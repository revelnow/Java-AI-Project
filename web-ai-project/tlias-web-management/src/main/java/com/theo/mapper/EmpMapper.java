package com.theo.mapper;

import com.theo.pojo.Emp;
import com.theo.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {


    /**
     * 查询所有员工及其对应的部门信息
     */
    //@Select("select emp.*, dept.name as dept_name from emp left join dept on emp.dept_id = dept.id")
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 添加员工信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键 -- 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values (#{username}, #{name}, #{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
}
