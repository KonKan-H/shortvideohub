package com.zzh.shortvideohub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zzh
 * @date 2020/3/5 19:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video extends PageBase {
    private Integer id;
    private Integer authorId;
    private String authorName;
    private String authorAvatar;
    private Date createTime;
    private String url;
    /**
     * 视频封面
     */
    private String cover;
    /**
     * 描述
     */
    private String description;
    /**
     * 时长
     */
    private Integer duration;
    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 下载数
     */
    private Integer downloads;
    /**
     * 评论数
     */
    private Integer comments;
    /**
     * 观看人
     */
    private Integer looker;

    /**
     * 热度
     */
    private Integer hot;

    /**
     * 状态 1为正常，0为封禁视频
     */
    private Integer status;
}
