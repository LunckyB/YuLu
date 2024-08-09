package com.yulu.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulu.entity.user.User;
import com.yulu.entity.user.bo.LoginBo;

import java.util.List;

public interface IUserService extends IService<User> {
    // 用户登录
    String userLogin(LoginBo loginBo);
    // 查询所有用户列表
    List<User> findUserList();
}
