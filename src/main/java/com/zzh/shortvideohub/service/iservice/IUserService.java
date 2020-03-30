package com.zzh.shortvideohub.service.iservice;

import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.pojo.Video;

import java.security.NoSuchAlgorithmException;

/**
 * @author zzh
 * @date 2020/2/28 16:20
 */
public interface IUserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    Integer registerUser(User user) throws NoSuchAlgorithmException;

    /**
     * 注册用户
     * @param user
     * @return
     */
    UserInfo userLogin(User user);

    /**
     * 用户信息更新
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 更新密码
     * @param user
     * @return
     */
    int updatePassword(User user);

    /**
     * 判断观看者是否关注了视频用户
     * @param video
     * @return
     */
    Boolean getAttentionOrNot(Video video);
}
