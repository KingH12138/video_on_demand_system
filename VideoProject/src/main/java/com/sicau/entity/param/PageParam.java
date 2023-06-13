package com.sicau.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jay
 * @description 统一分页入参
 * @create 2023/6/12 16:43
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "统一分页入参")
public class PageParam {
    @ApiModelProperty(value = "当前页码")
    private Integer currentPage = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 10;
}