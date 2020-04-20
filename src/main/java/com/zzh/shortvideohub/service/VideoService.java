package com.zzh.shortvideohub.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzh.shortvideohub.mapper.ReplyMapper;
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
    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 取得初始视频队列
     * @return
     */
    @Override
    public PageInfo<Video> getInitVideoList(UserInfo userInfo) {
        PageHelper.startPage(userInfo.getCurrentPage(), userInfo.getPageSize());
        List<Video> videoList = videoMapper.getInitVideoList(userInfo);
        PageInfo<Video> pageInfo = new PageInfo<Video>(videoList);
        if(userInfo.getCurrentPage() > pageInfo.getPages()) {
            pageInfo = null;
        }
        return pageInfo;
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
                videoMapper.deletePersonVideoLike(videoLiker);
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
     * 更新视频信息
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
            log.info("视频信息更新失败，数据：" + JSONObject.toJSONString(video));
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

    /**
     * 删除视频
     * @param video
     * @return
     */
    @Override
    public Boolean deleteVideo(Video video) {
        int row = videoMapper.deleteVideo(video);
        videoMapper.deleteVideoLike(video);
        replyMapper.deleteReplyByVideoId(video);
        return row == 1 ? true : false;
    }

    /**
     * 取得用户点赞视频
     * @param userInfo
     * @return
     */
    @Override
    public List<Video> getFavoriteVideo(UserInfo userInfo) {
        List<Video> videoList = videoMapper.getFavoriteVideo(userInfo);
        return videoList;
    }

    /**
     * 取得关注用户的视频
     * @param userInfo
     * @return
     */
    @Override
    public PageInfo<Video> getFollowingVideo(UserInfo userInfo) {
        PageHelper.startPage(userInfo.getCurrentPage(), userInfo.getPageSize());
        List<Video> videoList = videoMapper.getFollowingVideo(userInfo);
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        if(userInfo.getCurrentPage() > pageInfo.getPages()) {
            pageInfo = null;
        }
        return pageInfo;
    }
}
