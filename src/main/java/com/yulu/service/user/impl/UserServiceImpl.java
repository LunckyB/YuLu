package com.yulu.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulu.entity.user.User;
import com.yulu.mapper.user.UserMapper;
import com.yulu.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public List<User> findUserList() {
        return this.getBaseMapper().findUserList(new User());
    }
}
