package com.sicau.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.entity.po.Chapter;

import java.util.List;

/**
 * <p>
 * 作品章节 服务类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
public interface ChapterService extends IService<Chapter> {

    String saveChapter(Chapter chapter);

    void updateChapter(Chapter chapter);

    void deleteChapter(String id);

    Chapter getChapter(String id);

    List<Chapter> getChapterList();

    void deleteChapterByCourseId(String courseId);

    List<Chapter> getChapterListByCourseId(String courseId);
}
