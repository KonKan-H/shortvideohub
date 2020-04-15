package com.zzh.shortvideohub.controller;

import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.Reply;
import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/10 11:40
 */
@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    /**
     * 取得视频评论列表
     * @param video
     * @return
     */
    @RequestMapping(value = "/v1/reply/api", method = RequestMethod.POST)
    public Result<List<Reply>> getReplyList(@RequestBody Video video) {
        List<Reply> replyList = replyService.getReplyList(video);
        return new Result<>(1, replyList, "操作成功");
    }

    /**
     * 评论视频
     * @param reply
     * @return
     */
    @RequestMapping(value = "/v1/reply/api", method = RequestMethod.PUT)
    public Result<Integer> commentVideo(@RequestBody Reply reply) {
        int row = replyService.commentVideo(reply);
        if(row != 1) {
            return new Result<>(1, row, "操作失败");
        }
        return new Result<>(1, row, "操作成功");
    }

    /**
     * 删除评论
     * @param reply
     * @return
     */
    @PostMapping(value = "/v1/reply/delete/api")
    public Result<Integer> deleteReply(@RequestBody Reply reply) {
        int row = replyService.deleteReply(reply);
        if(row != 1) {
            return new Result<>(1, row, "操作失败");
        }
        return new Result<>(1, row, "操作成功");
    }

    /**
     * 点赞评论
     * @param reply
     * @return
     */
    @PostMapping(value = "/v1/reply/like/api")
    public Result<Integer> likeComment(@RequestBody Reply reply) {
        int row = replyService.likeComment(reply);
        return new Result<>(1, row, "操作成功");
    }

    /**
     * 取消点赞评论
     * @param reply
     * @return
     */
    @PostMapping(value = "/v1/reply/cancel/api")
    public Result<Integer> cancelComment(@RequestBody Reply reply) {
        int row = replyService.cancelComment(reply);
        return new Result<>(1, row, "操作成功");
    }

}
