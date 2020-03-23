package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.iservice.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Video> getInitVideoList() {
        List<Video> videoList = videoMapper.getInitVideoList();
        return videoList;
    }
}
