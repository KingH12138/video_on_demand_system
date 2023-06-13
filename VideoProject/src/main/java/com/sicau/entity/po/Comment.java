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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Comment对象
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "评论用户Id")
    private String userId;

    @ApiModelProperty(value = "评论课程id")
    private String courseId;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @TableField(value = "`gmt_create`", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "评论时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "回复用户id")
    private String replyId;

    @ApiModelProperty(value = "父评论id")
    private String parentId;

    @ApiModelProperty(value = "子评论列表")
    @TableField(exist = false)
    private List<Comment> replyList = new ArrayList<>();
}
