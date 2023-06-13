package com.sicau.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.entity.param.PageParam;
import com.sicau.entity.po.Comment;
import com.sicau.entity.vo.PageVo;
import com.sicau.mapper.CommentMapper;
import com.sicau.service.CommentService;
import com.sicau.utils.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    /**
     * @param comment : 添加评论
     * @return : void
     * @description 添加评论
     * @author jay
     * @create 2023/6/13 11:55
     */
    @Override
    public void saveComment(Comment comment) {
        this.save(comment);
    }

    /**
     * @param courseId  : 课程id
     * @param pageParam : 分页参数
     * @return : com.sicau.entity.vo.PageVo<com.sicau.entity.po.Comment>
     * @description 通过课程id分页查询评论
     * @author jay
     * @create 2023/6/13 11:55
     */
    @Override
    public PageVo<Comment> getCommentsByCourseId(String courseId, PageParam pageParam) {
        //直接查询所有一级评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>()
                .eq(Comment::getCourseId, courseId)
                .eq(Comment::getParentId, "-1")
                .orderByAsc(Comment::getGmtCreate);
        Page<Comment> commentPage = this.page(
                new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize()),
                queryWrapper);
        List<Comment> records = commentPage.getRecords();
        if (!ObjectUtils.isEmpty(records)) {
            records.forEach(this::getCommentsByParent);
        }
        commentPage.setRecords(records);
        return PageUtil.convert(commentPage);
    }

    /**
     * @param id : 评论id
     * @return : void
     * @description 删除评论及子评论
     * @author jay
     * @create 2023/6/13 11:55
     */
    @Override
    @Transactional
    public void deleteComment(String id) {
        // 删除所有子评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>()
                .eq(Comment::getParentId, id);
        List<Comment> commentList = this.list(queryWrapper);
        if (!ObjectUtils.isEmpty(commentList)) {
            commentList.forEach(comment -> {
                deleteComment(comment.getId());
            });
        }
        this.removeById(id);
    }

    /**
     * @param rootComment : 父评论
     * @return : void
     * @description 查询父评论下的所有子评论
     * @author jay
     * @create 2023/6/13 11:56
     */
    public void getCommentsByParent(Comment rootComment) {
        if (ObjectUtils.isEmpty(rootComment)) {
            return;
        }
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>()
                .eq(Comment::getParentId, rootComment.getId())
                .orderByAsc(Comment::getGmtCreate);
        List<Comment> commentList = this.list(queryWrapper);
        if (!ObjectUtils.isEmpty(commentList)) {
            commentList.forEach(comment -> {
                rootComment.getReplyList().add(comment);
                getCommentsByParent(comment);
            });
        }

    }
}
