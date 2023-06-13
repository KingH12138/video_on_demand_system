package com.sicau.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * <p>
 * 分页数据返回对象
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分页数据返回对象")
public class PageVo<T> {

    @ApiModelProperty(value = "当前页码")
    private int currentPage;

    @ApiModelProperty(value = "每页数据条数")
    private int pageSize;

    @ApiModelProperty(value = "总页数")
    private int pages;

    @ApiModelProperty(value = "总记录数")
    private int total;

    @ApiModelProperty(value = "数据")
    private List<T> records;

}