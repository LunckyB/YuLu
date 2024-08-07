package com.yulu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)  // 自动生成equals和hashcode方法, callSuper=true: 并在生成的代码中调用父类的equals和hashCode方法
@TableName(value = "regions")
public class Regions extends BaseEntity {
    // 高德地区表
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private String id; // 主键 对应高德的adcode

    private String name; // 名称

    private String parentId; // 上级id

    private String cityCode; // 城市编码

    private String longitude; // 经度

    private String latitude; // 纬度
}
