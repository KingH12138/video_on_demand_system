package com.sicau.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.entity.po.Chapter;
import com.sicau.entity.po.VideoContent;
import com.sicau.mapper.ChapterMapper;
import com.sicau.service.ChapterService;
import com.sicau.service.VideoContentVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 作品章节 服务实现类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoContentVideoService contentVideoService;

    /**
     * @param chapter : 章节对象
     * @return : java.lang.String
     * @description 添加章节
     * @author jay
     * @create 2023/6/13 11:50
     */
    @Override
    public String saveChapter(Chapter chapter) {
        String id = IdWorker.getIdStr();
        chapter.setId(id);
        this.save(chapter);
        return id;
    }

    /**
     * @param chapter : 章节对象
     * @return : void
     * @description 更新章节
     * @author jay
     * @create 2023/6/13 11:50
     */
    @Override
    public void updateChapter(Chapter chapter) {
        this.updateById(chapter);
    }

    /**
     * @param id : 章节id
     * @return : void
     * @description 删除章节，同时删除相关视频
     * @author jay
     * @create 2023/6/13 11:50
     */
    @Override
    @Transactional
    public void deleteChapter(String id) {
        this.removeById(id);
        //同时删除视频
        List<VideoContent> videoContentList = getVideoContentListByChapterId(id);
        if (!CollectionUtils.isEmpty(videoContentList)) {
            videoContentList.forEach(video -> {
                contentVideoService.deleteVideo(video.getVideoSourceId());
            });
        }

    }

    /**
     * @param id : 章节id
     * @return : com.sicau.entity.po.Chapter
     * @description 通过id查询章节
     * @author jay
     * @create 2023/6/13 11:50
     */
    @Override
    @Transactional
    public Chapter getChapter(String id) {
        Chapter chapter = this.getById(id);
        //查询所有章节视频
        List<VideoContent> videoContentList = getVideoContentListByChapterId(id);
        chapter.setVideoContentList(videoContentList);
        return chapter;
    }

    /**
     * @return : java.util.List<com.sicau.entity.po.Chapter>
     * @description 获取章节列表
     * @author jay
     * @create 2023/6/13 11:51
     */
    @Override
    @Transactional
    public List<Chapter> getChapterList() {
        List<Chapter> chapterList = this.list();
        if (!CollectionUtils.isEmpty(chapterList)) {
            {
                chapterList.forEach(item ->
                        item.setVideoContentList(getVideoContentListByChapterId(item.getId()))
                );
            }
        }
        return chapterList;
    }

    /**
     * @param chapterId : 章节id
     * @return : java.util.List<com.sicau.entity.po.VideoContent>
     * @description 通过章节id获取视频列表
     * @author jay
     * @create 2023/6/13 11:51
     */
    public List<VideoContent> getVideoContentListByChapterId(String chapterId) {
        LambdaQueryWrapper<VideoContent> queryWrapper = new LambdaQueryWrapper<VideoContent>()
                .eq(VideoContent::getChapterId, chapterId)
                .orderByAsc(VideoContent::getSort);
        List<VideoContent> videoContentList = contentVideoService.list(queryWrapper);
        return videoContentList;
    }

    /**
     * @param courseId : 课程id
     * @return : void
     * @description 通过课程id删除相关章节
     * @author jay
     * @create 2023/6/13 11:51
     */
    @Transactional
    @Override
    public void deleteChapterByCourseId(String courseId) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<Chapter>().eq(Chapter::getCourseId, courseId);
        List<Chapter> chapters = this.list(lambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(chapters)) {
            chapters.forEach(chapter -> {
                this.deleteChapter(chapter.getId());
            });
        }

    }

    /**
     * @param courseId : 课程id
     * @return : java.util.List<com.sicau.entity.po.Chapter>
     * @description 通过课程id获取章节列表
     * @author jay
     * @create 2023/6/13 11:52
     */
    @Override
    @Transactional
    public List<Chapter> getChapterListByCourseId(String courseId) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<Chapter>().eq(Chapter::getCourseId, courseId)
                .orderByAsc(Chapter::getSort);
        List<Chapter> chapters = this.list(lambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(chapters)) {
            chapters.forEach(chapter ->
                    chapter.setVideoContentList(getVideoContentListByChapterId(chapter.getId()))
            );
        }
        return chapters;
    }

}
