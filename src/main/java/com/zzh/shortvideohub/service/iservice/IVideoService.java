package com.zzh.shortvideohub.service.iservice;

import com.zzh.shortvideohub.pojo.Video;

import java.util.List;

/**
 * @author zzh
 * @date 2020/3/21 16:39
 */
public interface IVideoService {

    List<Video> getInitVideoList();
}
