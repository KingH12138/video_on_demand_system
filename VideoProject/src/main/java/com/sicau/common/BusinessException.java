package com.sicau.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jay
 * @description 异常处理
 * @create 2023/6/12 10:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private Integer code;
    private String msg;

    public BusinessException(String s) {
        super(s);
        this.code = ResultCode.ERROR;
        this.msg = s;
    }
}