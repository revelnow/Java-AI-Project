CREATE TABLE dept (
                      id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
                      name varchar(10) NOT NULL UNIQUE COMMENT '部门名称',
                      create_time datetime DEFAULT NULL COMMENT '创建时间',
                      update_time datetime DEFAULT NULL COMMENT '修改时间'
) COMMENT '部门表';
