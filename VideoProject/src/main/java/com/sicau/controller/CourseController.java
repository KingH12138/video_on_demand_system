package com.sicau.controller;


import com.sicau.common.ResultVo;
import com.sicau.entity.param.PageParam;
import com.sicau.entity.po.Course;
import com.sicau.entity.vo.PageVo;
import com.sicau.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 作品简介 前端控制器
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/course")
@Api(tags = "课程模块")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping()
    @ApiOperation("添加课程")
    public ResultVo addCourse(@RequestBody Course course) {
        if (ObjectUtils.isEmpty(course.getTitle()) || ObjectUtils.isEmpty(course.getDescription())) {
            return ResultVo.error().message("课程名称或简介不能为空");
        }
        String id = courseService.saveCourse(course);
        return ResultVo.ok().message("课程添加成功").data("courseId", id);
    }

    @PutMapping()
    @ApiOperation(value = "修改课程", notes = "修改只用修改基本信息")
    public ResultVo updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return ResultVo.ok().message("课程修改成功");
    }

    @DeleteMapping()
    @ApiOperation("删除课程")
    public ResultVo deleteCourse(String id) {
        courseService.deleteCourse(id);
        return ResultVo.ok().message("课程删除成功");
    }

    @GetMapping("{id}")
    @ApiOperation("通过id查询课程")
    public ResultVo getCourse(@PathVariable String id) {
        Course course = courseService.getCourse(id);
        return ResultVo.ok().message("课程查询成功").data("course", course);
    }

    @GetMapping("page")
    @ApiOperation("课程分页")
    public ResultVo getCoursePage(PageParam pageParam) {
        PageVo<Course> courseVoPageVo = courseService.getCoursePage(pageParam);
        return ResultVo.ok().message("课程分页查询成功").data("coursePage", courseVoPageVo);
    }
}

