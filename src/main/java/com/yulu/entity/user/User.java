package com.yulu.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yulu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)  // 自动生成equals和hashcode方法, callSuper=true: 并在生成的代码中调用父类的equals和hashCode方法
@TableName(value = "users")
public class User extends BaseEntity {
    // 用户表
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private String id; // 主键 guid

    private String realName; // 真实姓名

    private String cellPhone; // 手机号码

    private String headImg; // 用户头像

    private String nickname; // 昵称

    private String email; // 邮箱

    private Integer gender; // 性别 1: 男 2: 女

    private String birthday; // 生日

    private Integer certificateType; // 证件类型 1: 身份证

    private String certificateNo; // 证件号

    private String province; // 籍贯 - 省

    private String city; // 籍贯 - 市

    private String county; // 籍贯 - 县/区

    @JsonIgnore
    private String password; // 密码
}
