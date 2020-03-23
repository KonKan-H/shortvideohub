package com.zzh.shortvideohub.controller;

import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzh
 * @date 2020/3/21 15:38
 */
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/v1/video/api", method = RequestMethod.GET)
    public Result<List<Video>> getInitVideoList() {
        List<Video> videoList = videoService.getInitVideoList();
        if(videoList == null || videoList.size() ==0) {
            return new Result<>(0, null, "视频为空");
        }
        return new Result<List<Video>>(1, videoList, "为您推荐" + videoList.size() + "条视频");
    }
}
