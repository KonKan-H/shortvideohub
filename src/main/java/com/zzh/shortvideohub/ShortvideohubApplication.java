package com.zzh.shortvideohub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zzh.shortvideohub.mapper")
public class ShortvideohubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortvideohubApplication.class, args);
    }

}
