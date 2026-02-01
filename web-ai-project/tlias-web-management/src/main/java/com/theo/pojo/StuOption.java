package com.theo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuOption {
    private List clazzList; //班级列表
    private List dataList; //数据列表

}
