package com.sicau.controller;


import com.sicau.common.ResultVo;
import com.sicau.entity.po.Chapter;
import com.sicau.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 章节 前端控制器
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/chapter")
@Api(tags = "章节模块")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @PostMapping()
    @ApiOperation("添加章节")
    public ResultVo addChapter(@RequestBody Chapter chapter) {
        if (ObjectUtils.isEmpty(chapter.getTitle())) {
            return ResultVo.error().message("章节名称不能为空");
        }
        String id = chapterService.saveChapter(chapter);
        return ResultVo.ok().message("章节添加成功").data("chapterId", id);
    }

    @PutMapping()
    @ApiOperation(value = "修改章节", notes = "修改只用修改基本信息")
    public ResultVo updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateChapter(chapter);
        return ResultVo.ok().message("章节修改成功");
    }

    @DeleteMapping()
    @ApiOperation("删除章节")
    public ResultVo deleteChapter(String id) {
        chapterService.deleteChapter(id);
        return ResultVo.ok().message("章节删除成功");
    }

    @GetMapping("{id}")
    @ApiOperation("通过id查询章节")
    public ResultVo getChapter(@PathVariable String id) {
        Chapter chapter = chapterService.getChapter(id);
        return ResultVo.ok().message("章节查询成功").data("chapter", chapter);
    }

    @GetMapping("list")
    @ApiOperation("章节列表")
    public ResultVo getChapterPage() {
        List<Chapter> chapterList = chapterService.getChapterList();
        return ResultVo.ok().message("章节列表查询成功").data("chapterList", chapterList);
    }
}

