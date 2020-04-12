package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.ReplyMapper;
import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.Reply;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.iservice.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private VideoMapper videoMapper;
    /**
     * 根据视频取得评论列表
     * @param video
     * @return
     */
    @Override
    public List<Reply> getReplyList(Video video) {
        List<Reply> replies = replyMapper.getReplyListByVideo(video);
        List<Reply> firstReplies = new ArrayList<>();
        List<Reply> secondReplies = new ArrayList<>();
        for(Reply reply : replies) {
            if(reply.getReplyId() == null) {
                firstReplies.add(reply);
            } else {
                secondReplies .add(reply);
            }
        }
        for(Reply reply : firstReplies) {
            for(Reply r : secondReplies) {
                if(reply.getId() == r.getReplyId()) {
                    List<Reply> list = reply.getAfterReplies() == null ? new ArrayList<>() : reply.getAfterReplies();
                    list.add(r);
                    reply.setAfterReplies(list);
                }
            }
        }
        return firstReplies;
    }

    @Override
    public int commentVideo(Reply reply) {
        reply.setReplyTime(new Date());
        int row = replyMapper.insertReply(reply);
        videoMapper.addVideoComments(reply.getReplyVideoId());
        return 0;
    }
}
