package com.zzh.shortvideohub.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/2/26 19:45
 */
@Data
@RequiredArgsConstructor
public class User {
    private Integer id;
    private String mobilePhone;
    private String userName;
    private String userAvatar;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Date lastLoginTime;
    private String userAgent;
}
