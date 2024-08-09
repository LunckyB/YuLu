package com.yulu.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yulu.entity.system.Keys;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface KeysMapper extends BaseMapper<Keys> {
    @Select("select * from system_keys where expired_time > #{date} order by create_time desc limit 1")
    Keys getPubKey(Date date);
}
