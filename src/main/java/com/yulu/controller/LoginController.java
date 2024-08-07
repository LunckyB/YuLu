package com.yulu.controller;

import com.yulu.common.annotations.AutoPermission;
import com.yulu.common.exceptions.ServiceException;
import com.yulu.entity.user.User;
import com.yulu.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/login")
    public String login() {
        List<User> userList = iUserService.findUserList();
        return userList.get(0).getRealName();
    }

    @GetMapping("/login2")
    public String login2() {
        List<User> userList = iUserService.findUserList();
        return userList.get(0).getRealName();
    }
}
