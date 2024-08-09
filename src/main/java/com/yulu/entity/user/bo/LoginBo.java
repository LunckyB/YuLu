package com.yulu.entity.user.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginBo {

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的手机号")
    @Size(min = 11, max = 11, message = "请输入手机号")
    @NotEmpty(message = "请输入手机号")
    private String cellPhone; // 登录手机号

    @Size(min = 6, max = 64, message = "密码错误")
    @NotEmpty(message = "请输入手机号")
    private String pwd; // 登录密码

    // 登录的用户guid
    @JsonIgnore
    private String loginGuid;
    // 登录的用户id
    @JsonIgnore
    private String userId;
    // 登录时间
    @JsonIgnore
    private Long loginTime;
    // 登录过期时间
    @JsonIgnore
    private Long expireTime;

    private String token;
}
