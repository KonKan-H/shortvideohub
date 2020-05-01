package com.zzh.shortvideohub.pojo;

import lombok.Data;

/**
 * @author zzh
 * @date 2020/4/24 13:32
 */
@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String accessToken;
}
