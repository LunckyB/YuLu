package com.yulu.common.enums;

import org.springframework.context.annotation.Configuration;

/**
 * 常量类
 */
@Configuration
public class Constants {

    public static String TOKEN_CODE = "AuthCode"; // header token - 授权标识
    public static String TOKEN_CODE_PREFIX = "prefix "; // header token - 拼接前缀

    public static final String HTTP = "http://"; // http请求
    public static final String HTTPS = "https://"; // https请求

    public static final int REQUEST_SUCCESS = 200; // 请求响应成功码
    public static final int REQUEST_ERROR = 500; // 请求响应失败码

    // 权限标识前缀
    public static final String AUTO_PERMISSION_ROLES = "roles:"; // 角色权限校验前缀 oles:角色1|角色2
    public static final String AUTO_PERMISSION_PRIS = "pris:"; // 权限校验前缀 pris:权限1|权限2
    public static final String AUTO_PERMISSION_DEPTS = "depts:"; // 部门校验前缀 depts:部门id1|部门id2

    // 公私密钥有效时间
    public static final Integer EXPIRED_NUMBER = 6; // 单位: 小时
}
