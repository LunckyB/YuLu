package com.yulu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// 指定要扫描的Mapper类的包的路径
@MapperScan(basePackages = {"com.yulu.mapper.***"})
public class YuLuMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(YuLuMainApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  YuLu Run Success   ლ(´ڡ`ლ)ﾞ");
    }
}
