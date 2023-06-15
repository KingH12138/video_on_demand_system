package com.sicau.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @author jay
 * @description 阿里云工具类
 * @create 2023/6/12 10:12
 */
public class AliyunVodSDKUtils {

    /**
     * @param accessKeyId     : 访问密钥id
     * @param accessKeySecret : 访问密钥的密码
     * @return : com.aliyuncs.DefaultAcsClient
     * @description 初始化视频点播客户端
     * @author jay
     * @create 2023/6/13 11:58
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        // 点播服务接入区域 不能修改
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(
                regionId,
                accessKeyId,
                accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * @param videoId : 视频资源id
     * @return : java.lang.String
     * @description 获取视频凭证
     * @author jay
     * @create 2023/6/13 11:59
     */
    public static String init(String videoId) {
        try {
            final DefaultAcsClient defaultAcsClient = initVodClient(VodConstant.ACCESS_KEY_ID, VodConstant.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(videoId);
            // 调用方法得到凭证
            GetVideoPlayAuthResponse response = defaultAcsClient.getAcsResponse(request);
            // 获取凭证
            return response.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param videoId : 视频资源id
     * @return : java.lang.String
     * @description 获取视频播放地址
     * @author jay
     * @create 2023/6/13 11:59
     */
    public static String getUrl(String videoId) {
        try {
            final DefaultAcsClient defaultAcsClient = initVodClient(VodConstant.ACCESS_KEY_ID, VodConstant.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetPlayInfoRequest request = new GetPlayInfoRequest();
            //向request设置视频id
            request.setVideoId(videoId);
            // 调用方法得到播放地址
            GetPlayInfoResponse response = defaultAcsClient.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            GetPlayInfoResponse.PlayInfo playInfo = playInfoList.get(0);
            // 获取播放地址
            return playInfo.getPlayURL();
        } catch (ClientException e) {
            e.printStackTrace();
            return "";
        }
    }
}