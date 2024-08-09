package com.yulu.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulu.common.exceptions.ServiceException;
import com.yulu.common.tools.PwdTool;
import com.yulu.common.tools.TokenTool;
import com.yulu.common.tools.VerifyTool;
import com.yulu.entity.user.User;
import com.yulu.entity.user.bo.LoginBo;
import com.yulu.mapper.user.UserMapper;
import com.yulu.service.user.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    @Autowired
    TokenTool tokenTool;

    @Override
    public String userLogin(LoginBo loginBo) {
        String password = PwdTool.encodePasswordTo64(loginBo.getPwd());
        User user = this.getBaseMapper().findUserOne(loginBo.getCellPhone(), password);
        System.out.println(user.toString());
        if (!VerifyTool.isNotNull(user)) {
            throw new ServiceException("账号或密码错误");
        }

        loginBo.setUserId(user.getId());
        String token = tokenTool.userLoginCreateToken(loginBo);
        return token;
    }

    @Override
    public List<User> findUserList() {
        return this.getBaseMapper().findUserList(new User());
    }
}
