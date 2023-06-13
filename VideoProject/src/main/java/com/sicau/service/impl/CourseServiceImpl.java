package com.sicau.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.entity.param.PageParam;
import com.sicau.entity.po.Chapter;
import com.sicau.entity.po.Comment;
import com.sicau.entity.po.Course;
import com.sicau.entity.vo.PageVo;
import com.sicau.mapper.CourseMapper;
import com.sicau.service.ChapterService;
import com.sicau.service.CommentService;
import com.sicau.service.CourseService;
import com.sicau.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 作品简介 服务实现类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CommentService commentService;

    /**
     * @param course : 课程对象
     * @return : java.lang.String
     * @description 添加课程
     * @author jay
     * @create 2023/6/13 11:53
     */
    @Override
    public String saveCourse(Course course) {
        String id = IdWorker.getIdStr();
        course.setId(id);
        this.save(course);
        return id;
    }

    /**
     * @param course : 课程对象
     * @return : void
     * @description 修改课程
     * @author jay
     * @create 2023/6/13 11:53
     */
    @Override
    public void updateCourse(Course course) {
        this.updateById(course);
    }

    /**
     * @param id : 课程id
     * @return : void
     * @description 删除课程并删除章节和评论
     * @author jay
     * @create 2023/6/13 11:53
     */
    @Override
    public void deleteCourse(String id) {
        this.removeById(id);
        //删除相关章节
        chapterService.deleteChapterByCourseId(id);
        //删除相关评论
        List<Comment> commentList = commentService.list(new LambdaQueryWrapper<Comment>().eq(Comment::getCourseId, id));
        if (!ObjectUtils.isEmpty(commentList)) {
            commentList.forEach(comment -> {
                commentService.deleteComment(comment.getId());
            });
        }

    }

    /**
     * @param id : 课程id
     * @return : com.sicau.entity.po.Course
     * @description 通过id获取课程信息
     * @author jay
     * @create 2023/6/13 11:54
     */
    @Override
    public Course getCourse(String id) {
        Course course = this.getById(id);
        // 章节列表
        List<Chapter> chapterList = chapterService.getChapterListByCourseId(id);
        course.setChapterList(chapterList);
        return course;
    }

    /**
     * @param pageParam : 分页参数
     * @return : com.sicau.entity.vo.PageVo<com.sicau.entity.po.Course>
     * @description 课程分页
     * @author jay
     * @create 2023/6/13 11:54
     */
    @Override
    public PageVo<Course> getCoursePage(PageParam pageParam) {
        Page<Course> coursePage = this.page(
                new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize()));
        return PageUtil.convert(coursePage);
    }
}
