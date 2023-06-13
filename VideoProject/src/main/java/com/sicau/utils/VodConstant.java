package com.sicau.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author jay
 * @description 点播服务静态参数
 * @create 2023/6/12 10:13
 */
@Component
public class VodConstant implements InitializingBean {

    @Value("${aliyun.keyid}")
    private String keyId;

    @Value("${aliyun.keysecret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}