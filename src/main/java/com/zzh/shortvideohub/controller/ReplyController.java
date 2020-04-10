package com.zzh.shortvideohub.controller;

import com.zzh.shortvideohub.pojo.Reply;
import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/v1/reply/api", method = RequestMethod.POST)
    public Result<List<Reply>> getReplyList(@RequestBody Video video) {
        List<Reply> replyList = replyService.getReplyList(video);
        return new Result<>(1, replyList, "操作成功");
    }
}
