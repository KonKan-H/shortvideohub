package com.zzh.shortvideohub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/10 11:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private Integer id;
    //回复的评论
    private Integer replyId;
    private Integer replyVideoId;
    private Integer videoAuthorId;
    //评论人
    private Integer replyMakerId;
    private String replyMakerName;
    private String replyMakerAvatar;
    private String replyContent;
    private Date replyTime;
    private String ifFaved;
    private List<Reply> afterReplies;
}
