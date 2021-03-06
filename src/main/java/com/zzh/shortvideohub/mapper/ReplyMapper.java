package com.zzh.shortvideohub.mapper;

import com.zzh.shortvideohub.pojo.Reply;
import com.zzh.shortvideohub.pojo.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/10 11:33
 */
public interface ReplyMapper {

    /**
     * 根据视频取得评论列表
     *
     * @param video
     * @return
     */
    List<Reply> getReplyListByVideo(@Param("video") Video video);

    /**
     * 评论视频
     *
     * @param reply
     * @return
     */
    int insertReply(@Param("reply") Reply reply);

    /**
     * 删除视频评论 videoId
     *
     * @param video
     * @return
     */
    int deleteReplyByVideoId(@Param("video") Video video);

    /**
     * 删除视频评论 replyId
     *
     * @param reply
     * @return
     */
    int deleteReplyById(@Param("reply") Reply reply);

    /**
     * 评论喜欢数加一
     * @param reply
     * @return
     */
    int addReplyLikes(@Param("reply") Reply reply);

    /**
     * 评论喜欢数加一
     * @param reply
     * @return
     */
    int decreaseReplyLikes(@Param("reply") Reply reply);
}
