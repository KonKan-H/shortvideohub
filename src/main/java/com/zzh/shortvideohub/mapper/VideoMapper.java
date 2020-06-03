package com.zzh.shortvideohub.mapper;

import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.pojo.VideoLiker;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zzh
 * @date 2020/3/21 16:42
 */
@Repository
public interface VideoMapper {

    /**
     * 取得初始视频队列
     *
     * @return
     */
    List<Video> getInitVideoList(@Param("userInfo") UserInfo userInfo);

    /**
     * 取得用户视频
     * @param userInfo
     * @return
     */
    List<Video> getUserVideoList(@Param("userInfo") UserInfo userInfo);

    /**
     * 更新视频信息
     *
     * @param video
     */
    int updateVideo(@Param("video") Video video);

    /**
     * 视频收藏
     *
     * @param videoLiker
     * @return
     */
    int insertVideoLike(@Param("videoLiker") VideoLiker videoLiker);

    /**
     * 删除视频收藏
     *
     * @param videoLiker
     * @return
     */
    int deletePersonVideoLike(@Param("videoLiker") VideoLiker videoLiker);

    /**
     * 判断视频是否喜欢
     *
     * @param video
     * @return
     */
    int getVideoLikeOrNot(@Param("video") Video video);

    /**
     * 删除视频
     *
     * @param video
     * @return
     */
    int deleteVideo(@Param("video") Video video);

    /**
     * 取得用户点赞视频
     *
     * @param userInfo
     * @return
     */
    List<Video> getFavoriteVideo(@Param("userInfo") UserInfo userInfo);

    /**
     * 取得关注用户的视频
     *
     * @param userInfo
     * @return
     */
    List<Video> getFollowingVideo(@Param("userInfo") UserInfo userInfo);

    /**
     * 插入视频信息
     *
     * @param video
     * @return
     */
    int insertVideo(@Param("video") Video video);

    /**
     * 删除视频所有喜欢
     *
     * @param video
     * @return
     */
    int deleteVideoLike(@Param("video") Video video);

    /**
     * 增加视频评论数
     *
     * @return
     */
    int addVideoComments(int videoId);

    /**
     * 减少视频评论数
     *
     * @param VideoId
     * @return
     */
    int decreaseComments(int VideoId);

    /**
     * 取得所有的视频
     *
     * @return
     */
    List<Video> getAllVideos();

    /**
     * 同步视频的点赞数
     *
     * @param videos
     */
    void syncVideoLikes(@Param("videos") List<Video> videos);

    /**
     * 同步视频的评论数
     *
     * @param videos
     */
    void syncVideoComments(@Param("videos") List<Video> videos);

    /**
     * 视频热度
     *
     * @param videos
     */
    void videoHotUpdate(@Param("videos") List<Video> videos);

    /**
     * 取得热门视频
     *
     * @return
     */
    List<Video> getHotVideo();


}
