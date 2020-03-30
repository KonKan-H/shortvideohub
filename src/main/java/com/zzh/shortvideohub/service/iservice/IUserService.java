package com.zzh.shortvideohub.service.iservice;

import com.zzh.shortvideohub.pojo.*;

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
     * @param attention
     * @return
     */
    Boolean getAttentionOrNot(Attention attention);

    /**
     * 根据id查询userInfo
     * @param userId
     * @return
     */
    UserInfo getUserInfoById(Integer userId);

    /**
     * 关注
     * @param attention
     * @return
     */
    Boolean attentionUser(Attention attention);
}
