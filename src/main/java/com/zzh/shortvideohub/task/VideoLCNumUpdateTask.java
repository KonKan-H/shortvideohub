package com.zzh.shortvideohub.task;

import com.zzh.shortvideohub.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/20 15:12
 */
@Component
@Slf4j
public class VideoLCNumUpdateTask {

    @Autowired
    private VideoService videoService;

    @Scheduled(cron = "${task.videoLikesCommentsNumUpdate.cron}")
    public void videoLCNumUpdateTask() {
        log.info("视频点赞数评论数同步任务开始");
        videoService.videoLCNumUpdate();
        log.info("视频点赞数评论数同步任务结束");
    }

}
