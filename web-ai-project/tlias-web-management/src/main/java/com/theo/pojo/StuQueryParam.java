package com.theo.pojo;

import lombok.Data;

@Data
public class StuQueryParam {
    private String name; //学生姓名
    private Integer degree; //学历
    private Integer clazzId; //班级ID
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
}
