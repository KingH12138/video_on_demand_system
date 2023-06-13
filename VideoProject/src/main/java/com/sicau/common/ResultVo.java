package com.sicau.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jay
 * @description 统一返回对象
 * @create 2023/6/12 10:09
 */
@Data
public class ResultVo {

    private ResultVo() {
    }

    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private Map<String, Object> data = new HashMap<>();

    /**
     * 提供工具方法
     */
    public static ResultVo ok() {
        ResultVo r = new ResultVo();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static ResultVo info(String msg) {
        ResultVo r = new ResultVo();
        r.setSuccess(false);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage(msg);
        return r;
    }

    public static ResultVo error() {
        ResultVo r = new ResultVo();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public static ResultVo error(Throwable errorMessage) {
        ResultVo r = new ResultVo();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage(errorMessage.getLocalizedMessage());
        return r;
    }

    public static ResultVo error(String errorMessage) {
        ResultVo r = new ResultVo();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage(errorMessage);
        return r;
    }

    /**
     * 提供删除or更新
     */
    public static ResultVo toOk(boolean i) {
        ResultVo r = new ResultVo();
        r.setSuccess(i);
        r.setCode(i ? ResultCode.SUCCESS : ResultCode.ERROR);
        r.setMessage(i ? "操作成功" : "操作失败");
        return r;
    }


    public ResultVo success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultVo message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultVo code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultVo data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultVo data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public ResultVo data(Object data) {
        this.data.put("rows", data);
        return this;
    }

    public ResultVo dataPage(Object data, long total) {
        this.data.put("rows", data);
        this.data.put("total", total);
        return this;
    }
}