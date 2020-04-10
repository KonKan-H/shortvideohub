package com.zzh.shortvideohub.service.iservice;

import com.zzh.shortvideohub.pojo.Reply;
import com.zzh.shortvideohub.pojo.Video;

import java.util.List;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/10 11:28
 */
public interface IReplyService {
    /**
     * 根据视频取得评论列表
     * @param video
     * @return
     */
    List<Reply> getReplyList(Video video);
}
