package com.zzh.shortvideohub.service.iservice;

import com.zzh.shortvideohub.pojo.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
     * @param userInfo
     * @return
     */
    UserInfo getUserInfoById(UserInfo userInfo);

    /**
     * 关注
     * @param attention
     * @return
     */
    Boolean attentionUser(Attention attention);

    /**
     * 取得关注数和粉丝数
     * @param userInfo
     * @return
     */
    AttentionsFans getUserFansAndAttention(UserInfo userInfo);

    /**
     * 取消
     * @param attention
     * @return
     */
    Boolean cancelAttentionUser(Attention attention);

    /**
     * 取得粉丝列表
     * @param userInfo
     * @return
     */
    List<UserInfo> getFansList(UserInfo userInfo);

    /**
     * 取得关注列表
     * @param userInfo
     * @return
     */
    List<UserInfo> getAttentionsList(UserInfo userInfo);

    /**
     * 用户粉丝数关注数同步任务
     */
    void faNumSyncTask();
}
