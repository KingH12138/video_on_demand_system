package com.sicau.controller;


import com.sicau.common.BusinessException;
import com.sicau.common.ResultVo;
import com.sicau.entity.param.UploadVideoParam;
import com.sicau.service.VideoContentVideoService;
import com.sicau.utils.AliyunVodSDKUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 作品视频 前端控制器
 * </p>
 *
 * @author jay
 * @since 2023-06-12
 */
@RestController
@Slf4j
@RequestMapping("/vod")
@Api(tags = "视频模块")
public class VideoContentController {
    @Autowired
    private VideoContentVideoService vodService;

    @PostMapping("upload")
    @ApiOperation("上传视频，同时输入信息")
    public ResultVo uploadVideo(@RequestParam("file") MultipartFile file, UploadVideoParam param) {
        if (ObjectUtils.isEmpty(param.getTitle())) {
            return ResultVo.error().message("视频名称不能为空");
        }
        String videoId = vodService.uploadVideo(file, param);
        return ResultVo.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping()
    @ApiOperation("通过video_source_id删除视频，注意不是id")
    public ResultVo removeVideo(String videoId) {
        vodService.deleteVideo(videoId);
        return ResultVo.ok().message("视频删除成功");
    }

    @GetMapping("getPlayAuth/{id}")
    @ApiOperation(value = "根据视频id获取视频凭证")
    public ResultVo getPlayAuth(@PathVariable String id) {
        try {
            String playAuth = AliyunVodSDKUtils.init(id);
            return ResultVo.ok().data("playAuth", playAuth);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("获取凭证失败");
        }
    }

    @GetMapping("getPlayUrl/{id}")
    @ApiOperation(value = "根据视频id获取视频播放地址")
    public ResultVo getPlayUrl(@PathVariable String id) {
        try {
            String playUrl = AliyunVodSDKUtils.getUrl(id);
            return ResultVo.ok().data("playUrl", playUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("获取地址失败");
        }
    }

}

