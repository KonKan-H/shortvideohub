package com.zzh.shortvideohub.service.iservice;

import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.pojo.Video;

import java.util.List;

/**
 * @author zzh
 * @date 2020/3/21 16:39
 */
public interface IVideoService {

    /**
     * 取得初始视频队列
     * @return
     */
    List<Video> getInitVideoList();

    /**
     * 点赞
     * @param data
     * @return
     */
    int updateVideoLikes(JSONObject data);
}
