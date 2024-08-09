package com.yulu.common.enums;

import org.springframework.context.annotation.Configuration;

/**
 * 常量类
 */
@Configuration
public class Constants {
    public static String TOKEN_CODE = "AuthCode"; // header token - 授权标识
    public static String TOKEN_CODE_PREFIX = "Bearer "; // header token - 拼接前缀
    public static String LOGIN_USER_KEY = "login_user_id:"; // 登录的用户guid标识
    public static String LOGIN_TOKEN_KEY = "login_token:"; // 登录的用户token标识
    public static String LOGIN_GUID_KEY = "login_guid_key"; // 登录的用户key标识
    public static long MILLISECOND = 1000; // 换算成毫秒的计算值 1秒=1000毫秒

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

    // 密码加密值
    public static final String PASSWORD_ECODE = "YULU2024";
}
