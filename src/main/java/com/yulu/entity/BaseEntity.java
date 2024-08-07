package com.yulu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(fill = FieldFill.INSERT)
    private String createId; // 创建人

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateId; // 修改人

    @TableField(fill = FieldFill.INSERT)
    private String createTime; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime; // 修改时间

    @JsonIgnore                 // 添加JsonIgnore注解, 返回时会被忽略。例如: 将一个User序列化成json数据返回给前端, 加上该注解后, 即使该字段有值，也不会返回该字段
    private Integer del; // 假删除  1: 正常  0: 已删除
}
