package com.zzh.shortvideohub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author zzh
 * @date 2020/3/24 19:09
 */
@Data
@AllArgsConstructor
public class VideoLiker {
    private Integer id;
    private Integer videoId;
    private Integer liker;
    private Date createTime;
}
