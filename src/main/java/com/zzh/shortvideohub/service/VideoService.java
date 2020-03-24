package com.zzh.shortvideohub.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.pojo.VideoLiker;
import com.zzh.shortvideohub.service.iservice.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zzh
 * @date 2020/3/21 16:39
 */
@Service
@Transactional
@Slf4j
public class VideoService implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 取得初始视频队列
     * @return
     */
    @Override
    public List<Video> getInitVideoList() {
        List<Video> videoList = videoMapper.getInitVideoList();
        return videoList;
    }

    /**
     * 点赞
     * @param data
     * @return
     */
    @Override
    public int updateVideoLikes(JSONObject data) {
        Video video = JSONObject.parseObject(JSONObject.parse(JSONObject.toJSONString(data.getJSONObject("video"))).toString(),Video.class);
        int looker = Integer.valueOf(data.get("looker").toString());
        boolean isLiked = Boolean.valueOf(data.get("isLiked").toString());
        VideoLiker videoLiker = new VideoLiker(null, video.getId(), looker, new Date());
        try {
            if(isLiked) {
                video.setLikes(video.getLikes() + 1);
                videoMapper.insertVideoLike(videoLiker);
            } else {
                video.setLikes(video.getLikes() - 1);
                videoMapper.deleteVideoLike(videoLiker);
            }
            videoMapper.updateVideoLikes(video, looker);
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }
}
