package com.yulu.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yulu.entity.user.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    // 查询用户列表
    List<User> findUserList(User user);

    @Select("select * from users where id = #{id}")
    User findUserOne(String id);
}
