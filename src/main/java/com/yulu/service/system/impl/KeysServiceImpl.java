package com.yulu.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulu.common.enums.Constants;
import com.yulu.common.tools.DateTool;
import com.yulu.common.tools.RSATool;
import com.yulu.common.tools.VerifyTool;
import com.yulu.entity.system.Keys;
import com.yulu.mapper.system.KeysMapper;
import com.yulu.service.system.IKeysService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class KeysServiceImpl extends ServiceImpl<KeysMapper, Keys> implements IKeysService {
    @Override
    public Keys getPubKey() {
        // 查询数据库公私密钥配置
        Date date = DateTool.getDate();
        Keys keys = this.getBaseMapper().getPubKey(date);
        // 没有公私密钥或公私密钥已过期
        if (!VerifyTool.isNotNull(keys)) {
            // 生成新的公私密钥
            Map keysMap = RSATool.createPubPri();
            String pubkey = (String) keysMap.get("pubkey");
            String prikey = (String) keysMap.get("prikey");
            keys = new Keys();
            keys.setPubkey(pubkey);
            keys.setPrikey(prikey);
            keys.setCreateTime(date);
            Date expiredTime = DateTool.getDateAddHours(date, Constants.EXPIRED_NUMBER);
            keys.setExpiredTime(expiredTime);
            this.getBaseMapper().insert(keys);
        }

        return keys;
    }
}
