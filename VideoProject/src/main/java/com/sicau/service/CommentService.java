package com.sicau.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.entity.param.PageParam;
import com.sicau.entity.po.Comment;
import com.sicau.entity.vo.PageVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
public interface CommentService extends IService<Comment> {

    void saveComment(Comment commentVO);

    PageVo<Comment> getCommentsByCourseId(String courseId, PageParam pageParam);

    void deleteComment(String id);
}
