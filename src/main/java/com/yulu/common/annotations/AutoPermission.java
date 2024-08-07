package com.yulu.common.annotations;

import java.lang.annotation.*;

/**
 * 自动许可认证
 */
@Target(ElementType.METHOD) // 注解的目标为方法和类
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时可用
public @interface AutoPermission {
    /**
     * 格式: 类别:权限1,权限2
     *      类别: roles(角色校验), pris(权限校验), depts(部门权限)
     *
     *      oles:角色1|角色2
     *      pris:权限1|权限2(默认为权限校验, 可以不带pris. 示例: 权限1|权限2)
     *      depts:部门id1|部门id2
     */
    public String value() default "";
}
