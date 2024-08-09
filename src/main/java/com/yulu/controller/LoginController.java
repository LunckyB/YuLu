package com.yulu.controller;

import com.yulu.common.annotations.AutoPermission;
import com.yulu.common.exceptions.ServiceException;
import com.yulu.common.result.ResultMessage;
import com.yulu.common.tools.PwdTool;
import com.yulu.entity.system.Keys;
import com.yulu.entity.user.User;
import com.yulu.entity.user.bo.LoginBo;
import com.yulu.service.system.IKeysService;
import com.yulu.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IKeysService iKeysService;

    @PostMapping("/login")
    public ResultMessage login(@RequestBody @Validated() LoginBo loginBo) {
        String token = iUserService.userLogin(loginBo);
        return ResultMessage.token(token);
    }

    @PostMapping("/getPubKey")
    public ResultMessage getPubKey() {
        Keys keys = iKeysService.getPubKey();
        return ResultMessage.success(keys);
    }
}
