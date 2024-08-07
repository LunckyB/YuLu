package com.yulu.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulu.entity.user.User;

import java.util.List;

public interface IUserService extends IService<User> {
    // 查询所有用户列表
    List<User> findUserList();
}
