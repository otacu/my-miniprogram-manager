package com.egoist.miniprogram;

import com.egoist.miniprogram.config.MiniprogramConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableConfigurationProperties({MiniprogramConfig.class})
@SpringBootApplication
@MapperScan("com.egoist.miniprogram.dao")//将项目中对应的mapper类的路径加进来就可以了
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}