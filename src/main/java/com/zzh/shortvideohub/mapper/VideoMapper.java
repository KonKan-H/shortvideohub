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
    int deleteVideoLike(@Param("videoLiker") VideoLiker videoLiker);

    /**
     * 判断视频是否喜欢
     *
     * @param video
     * @return
     */
    int getVideoLikeOrNot(@Param("video") Video video);
}
