package com.sicau.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Course对象
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Course对象", description = "课程")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @TableField(value = "`gmt_create`", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(value = "`gmt_modified`", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

    @ApiModelProperty(value = "章节列表")
    @TableField(exist = false)
    private List<Chapter> chapterList;
}
