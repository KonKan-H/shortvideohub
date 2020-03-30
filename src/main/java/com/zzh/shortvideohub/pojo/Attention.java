package com.zzh.shortvideohub.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author zzh
 * @date 2020/3/30 19:02
 */
@Data
public class Attention {
    private Integer userId;
    private Integer fansId;
    private Date now;
}
