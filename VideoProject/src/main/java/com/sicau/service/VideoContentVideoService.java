package com.sicau.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.entity.param.UploadVideoParam;
import com.sicau.entity.po.VideoContent;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 作品视频 服务类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
public interface VideoContentVideoService extends IService<VideoContent> {

    void deleteVideo(String videoId);

    String uploadVideo(MultipartFile file, UploadVideoParam param);
}
