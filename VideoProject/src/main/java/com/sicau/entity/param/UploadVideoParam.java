package com.sicau.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jay
 * @description 视频上传入参
 * @create 2023/6/12 16:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "视频上传入参")
public class UploadVideoParam {

    @ApiModelProperty(value = "课程Id")
    private String courseId;

    @ApiModelProperty(value = "章节Id")
    private String chapterId;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}