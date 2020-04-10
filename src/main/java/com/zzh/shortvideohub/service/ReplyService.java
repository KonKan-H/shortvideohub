package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.ReplyMapper;
import com.zzh.shortvideohub.pojo.Reply;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.iservice.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/10 11:28
 */
@Service
@Transactional
public class ReplyService implements IReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 根据视频取得评论列表
     * @param video
     * @return
     */
    @Override
    public List<Reply> getReplyList(Video video) {
        List<Reply> replies = replyMapper.getReplyListByVideo(video);
        return replies;
    }
}
