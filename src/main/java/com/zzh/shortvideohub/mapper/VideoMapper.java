package com.zzh.shortvideohub.mapper;

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
    List<Video> getInitVideoList();

    /**
     * 点赞
     *
     * @param video
     * @param looker
     */
    int updateVideoLikes(@Param("video") Video video, @Param("looker") int looker);

    /**
     * 视频收藏
     *
     * @param videoLiker
     * @return
     */
    int insertVideoLike(@Param("videoLiker") VideoLiker videoLiker);

    /**
     * 删除视频收藏
     * @param videoLiker
     * @return
     */
    int deleteVideoLike(@Param("videoLiker") VideoLiker videoLiker);
}
