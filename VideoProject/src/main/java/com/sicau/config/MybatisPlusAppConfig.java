package com.sicau.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jay
 * @description mybatis-plus配置
 * @create 2023/6/12 10:26
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusAppConfig {

    /**
     * 配置分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}