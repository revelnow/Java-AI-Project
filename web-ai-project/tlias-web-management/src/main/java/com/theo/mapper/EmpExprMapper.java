package com.theo.mapper;

import com.theo.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    //void deleteByEmpIds(List<Integer> empIds);
}
