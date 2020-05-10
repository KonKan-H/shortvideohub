package com.zzh.shortvideohub.mapper;

import com.zzh.shortvideohub.pojo.Admin;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.pojo.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzh
 * @date 2020/4/24 13:45
 */
public interface AdminMapper {
    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    Admin checkAdmin(@Param("admin") Admin admin);

    /**
     * 取得用户列表
     *
     * @return
     */
    List<UserInfo> getUserList(@Param("userInfo") UserInfo userInfo);

    /**
     * 取得视频列表
     *
     * @return
     */
    List<Video> getVideoList(@Param("video") Video video);

    /**
     * 更改用户登录权限
     *
     * @param userInfo
     * @return
     */
    int changeUserStatus(@Param("userInfo") UserInfo userInfo);

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    int resetPass(@Param("user") User user);

    /**
     * 删除用户账号
     *
     * @param userInfo
     * @return
     */
    int deleteUser(@Param("userInfo") UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param userInfo
     * @return
     */
    int deleteUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 删除用户视频
     *
     * @param userInfo
     * @return
     */
    int deleteUserVideo(@Param("userInfo") UserInfo userInfo);

    /**
     * 删除用户关注
     *
     * @param userInfo
     * @return
     */
    int deleteUserAttentions(@Param("userInfo") UserInfo userInfo);

    /**
     * 删除用户粉丝
     *
     * @param userInfo
     * @return
     */
    int deleteUserFans(@Param("userInfo") UserInfo userInfo);

    /**
     * 删除用户评论
     *
     * @param userInfo
     * @return
     */
    int deleteUserReply(@Param("userInfo") UserInfo userInfo);

    /**
     * 删除用户喜欢
     *
     * @param userInfo
     * @return
     */
    int deleteUserVideoLike(@Param("userInfo") UserInfo userInfo);

    /**
     * 更改视频状态
     *
     * @param video
     * @return
     */
    int changeVideoStatus(@Param("video") Video video);

    /**
     * 查询待审核视频
     * @param video
     * @return
     */
    List<Video> getCheckVideo(@Param("video") Video video);
}
