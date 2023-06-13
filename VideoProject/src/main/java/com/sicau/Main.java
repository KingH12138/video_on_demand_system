package com.sicau;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jay
 * @description 启动入口
 * @create 2023/6/12 9:44
 */
@SpringBootApplication()
@MapperScan("com.sicau.mapper")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}