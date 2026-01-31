package com.theo.mapper;

import com.theo.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工的工作经历信息
     */
    void insertBatch(@Param("exprList") List<EmpExpr> exprList);



    /**
     * 根据员工ID批量删除员工工作经历
     */
    void deleteByEmpIds(List<Integer> empIds);

    /**
     * 根据员工ID查询员工的工作经历
     */
    @Select("select * from emp_expr where emp_id = #{id} order by begin desc")
    List<EmpExpr> getByEmpId(@Param("id") Integer id);


}
