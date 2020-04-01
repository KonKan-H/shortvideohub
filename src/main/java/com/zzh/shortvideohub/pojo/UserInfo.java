package com.zzh.shortvideohub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzh
 * @date 2020/3/7 16:09
 */
@Data
public class UserInfo {
    private Integer id;
    private Integer userId;
    private Integer age;
    private String userName;
    private String mobilePhone;
    private String userAvatar;
    private String sex;
    private String area;
    private String introduction;
    private Integer fans;
    private Integer attentions;
}
