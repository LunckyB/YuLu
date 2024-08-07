package com.yulu.entity.user.bo;

import lombok.Data;

@Data
public class LoginBo {

    private String cellPhone; // 登录手机号

    private String pwd; // 登录密码
}
