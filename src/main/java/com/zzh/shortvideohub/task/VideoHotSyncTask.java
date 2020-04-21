package com.zzh.shortvideohub.task;

import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/20 15:43
 */
@Slf4j
@Configuration
@EnableScheduling
public class VideoHotSyncTask {

    @Autowired
    private VideoService videoService;

    /**
     * 视频热度计算
     */
    @Scheduled(cron = "${task.videoHot.cron}")
    public void videoHotSync() {
        log.info("视频热度同步任务开始");
        videoService.videoHotSync();
        log.info("视频热度同步任务结束");
    }
}
