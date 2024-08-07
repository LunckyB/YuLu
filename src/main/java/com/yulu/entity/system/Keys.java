package com.yulu.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode // 自动生成equals和hashcode方法, callSuper=true: 并在生成的代码中调用父类的equals和hashCode方法
@TableName(value = "users")
public class Keys {
    // 公私密钥表
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private String pubkey; // 公钥

    private String prikey; // 私钥

    private Date expiredTime; // 过期时间

    private Date createTime; // 创建时间

}
