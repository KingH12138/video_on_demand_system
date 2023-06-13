package com.sicau.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.entity.param.PageParam;
import com.sicau.entity.po.Course;
import com.sicau.entity.vo.PageVo;

/**
 * <p>
 * 作品简介 服务类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
public interface CourseService extends IService<Course> {

    String saveCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(String id);

    Course getCourse(String id);

    PageVo<Course> getCoursePage(PageParam pageParam);
}
