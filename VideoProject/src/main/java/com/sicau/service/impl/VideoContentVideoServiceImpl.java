package com.sicau.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.common.BusinessException;
import com.sicau.common.ResultCode;
import com.sicau.entity.param.UploadVideoParam;
import com.sicau.entity.po.VideoContent;
import com.sicau.mapper.VideoContentVideoMapper;
import com.sicau.service.VideoContentVideoService;
import com.sicau.utils.AliyunVodSDKUtils;
import com.sicau.utils.VodConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 作品视频 服务实现类
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@Service
@Slf4j
public class VideoContentVideoServiceImpl extends ServiceImpl<VideoContentVideoMapper, VideoContent> implements VideoContentVideoService {

    /**
     * @param file  : 视频文件
     * @param param : 相关参数
     * @return : java.lang.String
     * @description 上传视频
     * @author jay
     * @create 2023/6/13 11:55
     */
    @Override
    public String uploadVideo(MultipartFile file, UploadVideoParam param) {
        try {
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            //截取标题
            assert originalFilename != null;
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            // 获取上传请求
            UploadStreamRequest request = new UploadStreamRequest(
                    VodConstant.ACCESS_KEY_ID,
                    VodConstant.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);
            // 开始上传
            UploadVideoImpl uploader = new UploadVideoImpl();
            // 上传完毕后,服务器给于响应
            UploadStreamResponse response = uploader.uploadStream(request);
            // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.error(errorMessage);
                if (StringUtils.isEmpty(videoId)) {
                    throw new BusinessException(ResultCode.ERROR, errorMessage);
                }
            }

            VideoContent videoContent = new VideoContent();
            videoContent.setCourseId(param.getCourseId());
            videoContent.setChapterId(param.getChapterId());
            videoContent.setTitle(param.getTitle());
            videoContent.setVideoSourceId(videoId);
            videoContent.setVideoOriginalName(originalFilename);
            videoContent.setSort(param.getSort());
            videoContent.setSize(file.getSize() / 8);

            this.save(videoContent);
            return videoId;
        } catch (IOException e) {
            throw new BusinessException(ResultCode.ERROR, "vod 服务上传失败");
        }
    }

    /**
     * @param videoId : 通过视频资源id
     * @return : void
     * @description 删除视频
     * @author jay
     * @create 2023/6/13 11:55
     */
    @Override
    public void deleteVideo(String videoId) {
        try {
            //获取客户端请求
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    VodConstant.ACCESS_KEY_ID,
                    VodConstant.ACCESS_KEY_SECRET);
            //创建删除请示
            DeleteVideoRequest request = new DeleteVideoRequest();
            //设置要删除的视频ID
            request.setVideoIds(videoId);
            //删除请求后给予的响应
            DeleteVideoResponse response = client.getAcsResponse(request);
            LambdaQueryWrapper<VideoContent> queryWrapper = new LambdaQueryWrapper<VideoContent>().eq(VideoContent::getVideoSourceId, videoId);
            this.remove(queryWrapper);
            log.info("删除视频资源成功:{}", response);
        } catch (ClientException e) {
            throw new BusinessException(ResultCode.ERROR, "视频删除失败");
        }
    }
}
