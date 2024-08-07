package com.yulu.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulu.entity.system.Keys;
import com.yulu.mapper.system.KeysMapper;
import com.yulu.service.system.IKeysService;
import org.springframework.stereotype.Service;

@Service
public class KeysServiceImpl extends ServiceImpl<KeysMapper, Keys> implements IKeysService {
}
