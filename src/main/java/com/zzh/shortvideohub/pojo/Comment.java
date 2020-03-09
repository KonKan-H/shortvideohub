package com.zzh.shortvideohub.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author zzh
 * @date 2020/3/9 17:44
 */
@Data
public class Comment {
    private Integer id;
    private String content;
    private Integer authorId;
    private String authorName;
    private Integer replyContentId;
    private String replyContent;
    private Integer replyAuthorId;
    private String replyAuthorName;
    private Integer likes;
    private Date createTime;
}
