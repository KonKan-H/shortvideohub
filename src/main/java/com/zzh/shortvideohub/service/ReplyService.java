package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.ConstantCache.ConstantCache;
import com.zzh.shortvideohub.mapper.ReplyMapper;
import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.Reply;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.iservice.IReplyService;
import com.zzh.shortvideohub.util.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private RedisService redisService;
    /**
     * 根据视频取得评论列表
     * @param video
     * @return
     */
    @Override
    public List<Reply> getReplyList(Video video) {
        List<Reply> replies = replyMapper.getReplyListByVideo(video);
//        for(Reply reply : replies) {
//            reply.setIfFaved("no");
//            String key = ConstantCache.LIKE_COMMENT_LABEL(reply.getId(),video.getLooker());
//            Object o = redisService.get(key);
//            if(o != null && o.toString().equals("1")) {
//                reply.setIfFaved("yes");
//            }
//        }
        return replies;
    }

    /**
     * 评论视频
     * @param reply
     * @return
     */
    @Override
    public int commentVideo(Reply reply) {
        reply.setReplyTime(new Date());
        reply.setLikes(0);
        int row = replyMapper.insertReply(reply);
        if(row == 1) {
            videoMapper.addVideoComments(reply.getReplyVideoId());
        }
        return row;
    }

    /**
     * 删除评论
     * @param reply
     * @return
     */
    @Override
    public int deleteReply(Reply reply) {
        int row = replyMapper.deleteReplyById(reply);
        if(row == 1) {
            videoMapper.decreaseComments(reply.getReplyVideoId());
        }
        return row;
    }

    /**
     * 点赞评论
     * @param reply
     * @return
     */
    @Override
    public int likeComment(Reply reply) {
        String label = ConstantCache.LIKE_COMMENT_LABEL(reply.getId(), reply.getLikePeople());
        redisService.set(label, "1");
        replyMapper.addReplyLikes(reply);
        return 1;
    }

    /**
     * 取消点赞评论
     * @param reply
     * @return
     */
    @Override
    public int cancelComment(Reply reply) {
        String label = ConstantCache.LIKE_COMMENT_LABEL(reply.getId(), reply.getLikePeople());
        redisService.remove(label);
        replyMapper.decreaseReplyLikes(reply);
        return 1;
    }
}
