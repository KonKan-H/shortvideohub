package com.zzh.shortvideohub.service;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.UserInfo;
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
    public List<Video> getInitVideoList(UserInfo userInfo) {
        List<Video> videoList = videoMapper.getInitVideoList(userInfo);
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
                videoMapper.insertVideoLike(videoLiker);
            } else {
                videoMapper.deleteVideoLike(videoLiker);
            }
            videoMapper.updateVideo(video);
        } catch (Exception e) {
            log.info(e.toString());
            log.info("点赞失败， 数据：" + JSONObject.toJSONString(video));
            return -1;
        }
        return 1;
    }

    /**
     * 更新视频下载信息
     * @param video
     * @return
     */
    @Override
    public int updateVideo(Video video) {
        int row = 0;
        try {
            row = videoMapper.updateVideo(video);
        } catch (Exception e) {
            log.info(e.toString());
            log.info("下载信息更新失败，数据：" + JSONObject.toJSONString(video));
        }
        return row;
    }

    /**
     * 判断视频是否喜欢
     * @param video
     * @return
     */
    @Override
    public Boolean getVideoLikeOrNot(Video video) {
        int row = videoMapper.getVideoLikeOrNot(video);
        Boolean flag = row == 1 ? true : false;
        return flag;
    }

    @Override
    public Boolean deleteVideo(Video video) {
        int row = videoMapper.deleteVideo(video);
        return row == 1 ? true : false;
    }
}
