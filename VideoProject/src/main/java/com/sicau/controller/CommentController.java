package com.sicau.controller;


import com.sicau.common.ResultVo;
import com.sicau.entity.param.PageParam;
import com.sicau.entity.po.Comment;
import com.sicau.entity.vo.PageVo;
import com.sicau.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论模块")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    @ApiOperation("添加评论或回复")
    public ResultVo saveComment(@RequestBody Comment comment) {
        if (ObjectUtils.isEmpty(comment.getCommentContent())) {
            return ResultVo.error().message("评论内容不能为空");
        }
        commentService.saveComment(comment);
        return ResultVo.ok().message("评论成功");
    }

    @GetMapping("/{courseId}")
    @ApiOperation("通过课程id查询所有评论")
    public ResultVo getCommentsByCourseId(@PathVariable String courseId, PageParam pageParam) {
        PageVo<Comment> commentVoPageVo = commentService.getCommentsByCourseId(courseId, pageParam);
        return ResultVo.ok().data("commentPage", commentVoPageVo);
    }

    @DeleteMapping()
    @ApiOperation("通过id删除评论")
    public ResultVo deleteComment(String id) {
        commentService.deleteComment(id);
        return ResultVo.ok().message("删除评论成功");
    }
}

