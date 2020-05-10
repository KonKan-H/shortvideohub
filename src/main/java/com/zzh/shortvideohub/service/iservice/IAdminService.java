package com.zzh.shortvideohub.service.iservice;

import com.github.pagehelper.PageInfo;
import com.zzh.shortvideohub.pojo.Admin;
import com.zzh.shortvideohub.pojo.PageBase;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.pojo.Video;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author zzh
 * @date 2020/4/24 13:40
 */
public interface IAdminService {

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    Admin adminLogin(Admin admin) throws NoSuchAlgorithmException;

    /**
     * 取得用户列表
     * @return
     */
    PageInfo<UserInfo> getUserList(UserInfo userInfo);

    /**
     * 取得视频列表
     * @param video
     * @return
     */
    PageInfo<Video> getVideoList(Video video);

    /**
     * 更改用户登录权限
     * @param userInfo
     * @return
     */
    int changeUserStatus(UserInfo userInfo);

    /**
     * 重置密码
     * @param userInfo
     * @return
     */
    boolean resetPass(UserInfo userInfo) throws NoSuchAlgorithmException;

    /**
     * 删除用户账号
     * @param userInfo
     * @return
     */
    boolean deleteUser(UserInfo userInfo);

    /**
     * 更改视频权限
     * @param video
     * @return
     */
    int changeVideoStatus(Video video);

    /**
     * 查询待审核视频
     * @param video
     * @return
     */
    PageInfo<Video> getCheckVideo(Video video);
}
