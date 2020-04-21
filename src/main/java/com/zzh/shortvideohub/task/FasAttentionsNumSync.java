package com.zzh.shortvideohub.task;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/20 14:32
 */

import com.zzh.shortvideohub.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 用户粉丝数关注数同步任务
 */
@Configuration
@EnableScheduling
@Slf4j
public class FasAttentionsNumSync {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "${task.fnsAttentionsNumUpdate.cron}")
    public void faNumSyncTask() {
        log.info("用户粉丝数关注数同步任务开始");
        userService.faNumSyncTask();
        log.info("用户粉丝数关注数同步任务结束");
    }
}
