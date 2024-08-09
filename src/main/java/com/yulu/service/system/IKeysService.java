package com.yulu.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulu.entity.system.Keys;

public interface IKeysService extends IService<Keys> {
    // 获取公钥
    Keys getPubKey();
}
