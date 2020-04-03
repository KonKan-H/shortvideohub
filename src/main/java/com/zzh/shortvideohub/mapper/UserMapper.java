package com.zzh.shortvideohub.mapper;

import com.zzh.shortvideohub.pojo.Attention;
import com.zzh.shortvideohub.pojo.AttentionsFans;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zzh
 * @date 2020/2/28 16:32
 */
@Repository
public interface UserMapper {

    /**
     * 根据手机号查询用户
     *
     * @param user
     * @return
     */
    User getUserByMP(@Param("user") User user);

    /**
     * 注册用户
     *
     * @return
     */
    Integer insertUser(User user);

    /**
     * 验证用户
     *
     * @param user
     * @return
     */
    int checkUser(@Param("user") User user);

    /**
     * 创建默认用户信息
     *
     * @param userInfo
     * @return
     */
    int insertUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 取得用户具体信息
     *
     * @param user
     * @return
     */
    UserInfo getUserInfo(@Param("user") User user);

    /**
     * 用户信息更新
     *
     * @param userInfo
     * @return
     */
    int updateUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    int updatePassword(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUser(@Param("user") User user);

    /**
     * 判断观看者是否关注了视频用户
     *
     * @param attention
     * @return
     */
    int selectAttentionOrNot(@Param("attention") Attention attention);

    /**
     * 根据id查询userInfo
     *
     * @param userId
     * @return
     */
    UserInfo selectUserInfoById(@Param("userId") Integer userId);

    /**
     * 关注
     *
     * @param attention
     * @return
     */
    int attentionUser(@Param("attention") Attention attention);

    /**
     * 增加粉丝数
     *
     * @param attention
     * @return
     */
    int insertFansNum(@Param("attention") Attention attention);

    /**
     * 增加关注数
     *
     * @param attention
     * @return
     */
    int insertAttentionsNum(@Param("attention") Attention attention);

    /**
     * 减少粉丝数
     *
     * @param attention
     * @return
     */
    int decreaseFansNum(@Param("attention") Attention attention);

    /**
     * 减少关注数
     *
     * @param attention
     * @return
     */
    int decreaseAttentionNum(@Param("attention") Attention attention);

    /**
     * 取得关注数和粉丝数
     *
     * @param userInfo
     * @return
     */
    AttentionsFans getUserFansAndAttention(@Param("userInfo") UserInfo userInfo);

    /**
     * 取消关注
     *
     * @param attention
     * @return
     */
    int cancelAttentionUser(@Param("attention") Attention attention);

    /**
     * 取得粉丝列表
     *
     * @param userInfo
     * @return
     */
    List<UserInfo> getFansList(@Param("userInfo") UserInfo userInfo);

    /**
     * 取得关注列表
     * @param userInfo
     * @return
     */
    List<UserInfo> getAttentionsList(@Param("userInfo") UserInfo userInfo);
}
