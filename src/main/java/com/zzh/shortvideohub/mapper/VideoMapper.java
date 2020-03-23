package com.zzh.shortvideohub.mapper;

import com.zzh.shortvideohub.pojo.Video;
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
     * @return
     */
    List<Video> getInitVideoList();

}
