package com.yulu.common.tools;

import com.yulu.common.enums.Constants;
import com.yulu.common.redis.RedisTool;
import com.yulu.entity.user.bo.LoginBo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TokenTool {
    private static final Logger log = LoggerFactory.getLogger(TokenTool.class);

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（单位: 秒）
    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private RedisTool redisTool;

    /**
     * 用户登录生成token
     * @param loginBo
     * @return
     */
    public String userLoginCreateToken(LoginBo loginBo) {
        // 1. 设置登录时间和登录过期时间
        loginBo.setLoginTime(System.currentTimeMillis());
        loginBo.setExpireTime(loginBo.getLoginTime() + expireTime * Constants.MILLISECOND); // expireTime为秒值, 需要换算成毫秒值
        // 2. 【uuid】: 生成贯穿整个登录的uuid
        String guid = UUIDTool.fastSimpleUUID();
        loginBo.setLoginGuid(guid);
        // 3. 【userIdKey】: 生成redis的用户id key
        String userIdKey = Constants.LOGIN_USER_KEY + loginBo.getUserId();
        //    【loginGuidKey】: 根据【uuid】, 生成登录缓存key
        String loginGuidKey = Constants.LOGIN_TOKEN_KEY + loginBo.getLoginGuid();
        // 4. 根据【userIdKey】去redis中查找是否存在，如果存在就移除
        String guidRedisValue = redisTool.getRedisCache(userIdKey);
        if (guidRedisValue != null) {
            redisTool.deleteRedisCache(userIdKey);
            redisTool.deleteRedisCache(loginGuidKey);
        }
        // 5. 根据【loginGuidKey】 缓存整个登录信息
        redisTool.setRedisCache(loginGuidKey, loginBo, expireTime, TimeUnit.SECONDS);
        // 6. 根据【userIdKey】, 缓存【loginGuidKey】
        redisTool.setRedisCache(userIdKey, loginGuidKey, expireTime, TimeUnit.SECONDS);
        // 7. 返回token
        return createToken(loginBo.getLoginGuid());
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (VerifyTool.isNotEmpty(token) && token.startsWith(Constants.TOKEN_CODE_PREFIX)) {
            token = token.replace(Constants.TOKEN_CODE_PREFIX, "");
        }
        return token;
    }

    /**
     * 生成token
     * @param guid: 贯穿整个登录的uuid
     * @return token
     */
    private String createToken(String guid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_GUID_KEY, guid);
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    /**
     * 从token中解密数据
     * @param token
     * @return
     */
    private Claims decryptToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
