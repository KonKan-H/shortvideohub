package com.zzh.shortvideohub.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zzh.shortvideohub.pojo.PageBase;
import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzh
 * @date 2020/3/21 15:38
 */
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 取得初始化视频列表
     * @return
     */
    @RequestMapping(value = "/v1/video/init/api", method = RequestMethod.POST)
    public Result<PageInfo<Video>> getInitVideoList(@RequestBody UserInfo userInfo) {
        PageInfo<Video> videoList = videoService.getInitVideoList(userInfo);
        if(videoList == null || videoList.getList().size() ==0) {
            return new Result<>(0, null, "视频为空");
        }
        return new Result<PageInfo<Video>>(1, videoList, "为您推荐" + videoList.getList().size() + "条视频");
    }

    /**
     * 更新视频喜欢数
     * @param data
     * @return
     */
    @RequestMapping(value = "/v1/like/api", method = RequestMethod.PUT)
    public Result<Boolean> updateVideoLikes(@RequestBody JSONObject data) {
        int key = videoService.updateVideoLikes(data);
        if(key != 1) {
            return new Result<Boolean>(-1, false, "操作失败");
        }
        return new Result<Boolean>(1, true, "操作成功");
    }

    /**
     * 更新视频
     * @param video
     * @return
     */
    @RequestMapping(value = "/v1/video/api", method = RequestMethod.PUT)
    public  Result<Integer> updateVideo(@RequestBody Video video) {
        int key = videoService.updateVideo(video);
        if(key != 1) {
            return new Result<Integer>(-1, 0, "操作失败");
        }
        return new Result<Integer>(1, 1, "操作成功");
    }

    /**
     * 判断视频是否喜欢
     * @param video
     * @return
     */
    @RequestMapping(value = "/v1/like/api", method = RequestMethod.POST)
    public Result<Boolean> getVideoLikeOrNot(@RequestBody Video video) {
        Boolean flag = videoService.getVideoLikeOrNot(video);
        return new Result<Boolean>(1, flag, "操作成功");
    }

    /**
     * 删除视频
     * @param video
     * @return
     */
    @RequestMapping(value = "/v1/video/delete/api", method = RequestMethod.POST)
    public Result<Boolean> deleteVideo(@RequestBody Video video) {
        Boolean flag = videoService.deleteVideo(video);
        return new Result<>(1, flag, "操作成功");
    }

    /**
     * 取得用户点赞视频
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/v1/favorite/video/api", method = RequestMethod.POST)
    public Result<List<Video>> getFavoriteVideo(@RequestBody UserInfo userInfo) {
        List<Video> videoList = videoService.getFavoriteVideo(userInfo);
        return new Result<>(1, videoList, "操作成功");
    }

    /**
     * 取得关注用户的视频
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/v1/following/video/api", method = RequestMethod.POST)
    public Result<PageInfo<Video>> getFollowingVideo(@RequestBody UserInfo userInfo) {
        PageInfo<Video> videoList = videoService.getFollowingVideo(userInfo);
        return new Result<>(1, videoList, "操作成功");
    }

    @PostMapping(value = "/v1/hot/video/api")
    public Result<PageInfo<Video>> getHotVideo(@RequestBody PageBase pageBase) {
        PageInfo<Video> videoPageInfo = videoService.getHotVideo(pageBase);
        return new Result<>(1, videoPageInfo, "操作成功");
    }
}