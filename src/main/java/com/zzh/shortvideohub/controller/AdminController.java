package com.zzh.shortvideohub.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zzh.shortvideohub.pojo.*;
import com.zzh.shortvideohub.service.AdminService;
import com.zzh.shortvideohub.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author zzh
 * @date 2020/4/24 12:42
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VideoService videoService;

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/api")
    public Result<Admin> adminLogin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        Admin a = adminService.adminLogin(admin);
        if(a != null) {
            return new Result<>(1, a, "操作成功");
        }
        return new Result<>(0, null, "操作失败");
    }

    /**
     * 取得用户列表
     * @param userInfo
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/user/list/api")
    public Result<PageInfo<UserInfo>>  getUserList(@RequestBody UserInfo userInfo) {
        PageInfo<UserInfo> pageInfo = adminService.getUserList(userInfo);
        return new Result<>(1, pageInfo, "操作成功");
    }

    /**
     * 取得视频列表
     * @param video
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/video/list/api")
    public Result<PageInfo<Video>> getVideoList(@RequestBody Video video) {
        PageInfo<Video> pageInfo = adminService.getVideoList(video);
        return new Result<>(1, pageInfo, "操作成功");
    }

    /**
     * 查询待审核视频
     * @param video
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/video/check/api")
    public Result<PageInfo<Video>> getCheckVideo(@RequestBody Video video) {
        PageInfo<Video> pageInfo = adminService.getCheckVideo(video);
        return new Result<>(1, pageInfo, "操作成功");
    }

    /**
     * 管理员删除视频
     * @param video
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/video/delete/api")
    public Result<Boolean> deleteVideo(@RequestBody Video video) {
        boolean flag = videoService.deleteVideo(video);
        if(!flag) {
            return new Result<>(0, flag, "操作失败");
        }
        return new Result<>(1, flag, "操作成功");
    }

    /**
     * 更改用户登录权限
     * @param userInfo
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/user/status/api")
    public Result<Boolean> changeUserStatus(@RequestBody UserInfo userInfo) {
        int num = adminService.changeUserStatus(userInfo);
        return new Result<>(1, num == 1 ? true : false, "操作成功");
    }

    /**
     * 更改视频权限
     * @param video
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/video/status/api")
    public Result<Boolean> changeVideoStatus(@RequestBody Video video) {
        int num = adminService.changeVideoStatus(video);
        return new Result<>(1, num == 1 ? true : false, "操作成功");
    }

    /**
     * 重置密码
     * @param userInfo
     * @return
     * @throws NoSuchAlgorithmException
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/password/reset/api")
    public Result<Boolean> resetPass(@RequestBody UserInfo userInfo) throws NoSuchAlgorithmException {
        boolean flag = adminService.resetPass(userInfo);
        if(!flag) {
            return new Result<>(0, flag, "操作失败");
        }
        return new Result<>(1, flag, "操作成功");
    }

    /**
     * 删除用户
     * @param userInfo
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/v1/admin/user/delete/api")
    public Result<Boolean> deleteUser(@RequestBody UserInfo userInfo) {
        boolean flag = adminService.deleteUser(userInfo);
        if(!flag) {
            return new Result<>(0, flag, "操作失败");
        }
        return new Result<>(1, flag, "操作成功");
    }
}
