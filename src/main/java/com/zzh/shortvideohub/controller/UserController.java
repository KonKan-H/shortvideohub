package com.zzh.shortvideohub.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zzh.shortvideohub.pojo.*;
import com.zzh.shortvideohub.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author zzh
 * @date 2020/2/27 15:10
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/v1/registration/api", method = RequestMethod.POST)
    public Result<Integer> registration(@RequestBody User user) throws NoSuchAlgorithmException {
        Integer row = userService.registerUser(user);
        if(row == 1) {
            return new Result<Integer>(1, 1, "操作成功");
        }
        return new Result<Integer>(1, 0, "操作失败");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/v1/login/api", method = RequestMethod.POST)
    public Result<UserInfo> userLogin(@RequestBody User user) {
        UserInfo userInfo = userService.userLogin(user);
        if(userInfo == null) {
            return new Result<UserInfo>(-1, null, "用户名或密码错误");
        }
        return new Result<UserInfo>(1, userInfo, "登录成功");
    }

    /**
     * 用户信息更新
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/v1/userInfo/api", method = RequestMethod.PUT)
    public Result<UserInfo> updateUsserInfo(@RequestBody UserInfo userInfo) {
        int row = userService.updateUserInfo(userInfo);
        if(row == 1) {
            return new Result<UserInfo>(1, userInfo, "更新成功");
        }
        return new Result<UserInfo>(-1, null, "更新失败");
    }

    /**
     * 更新密码
     * @param user
     * @return
     */
    @RequestMapping(value = "/v1/password/api", method = RequestMethod.PUT)
    public Result<Integer> updatePassword(@RequestBody User user) {
        int row = userService.updatePassword(user);
        if(row != 1) {
            return new Result<Integer >(-1, 0, "操作失败");
        }
        return new Result<Integer>(1, 1, "操作成功");
    }

    /**
     * 判断观看者是否关注了视频用户
     * @param attention
     * @return
     */
    @RequestMapping(value = "/v1/attention/api", method = RequestMethod.POST)
    public Result<Boolean> getAttentionOrNot(@RequestBody Attention attention) {
        Boolean flag = userService.getAttentionOrNot(attention);
        return new Result<Boolean>(1, flag, "操作成功");
    }

    /**
     * 关注
     * @param attention
     * @return
     */
    @RequestMapping(value = "/v1/attention/user/insert/api", method = RequestMethod.POST)
    public Result<Boolean> attentionUser(@RequestBody Attention attention) {
        Boolean flag = userService.attentionUser(attention);
        return new Result<>(1, flag, "关注成功");
    }

    /**
     * 取消关注
     * @param attention
     * @return
     */
    @RequestMapping(value = "/v1/attention/user/cancel/api", method = RequestMethod.POST)
    public Result<Boolean> cancelAttentionUser(@RequestBody Attention attention) {
        Boolean flag = userService.cancelAttentionUser(attention);
        return new Result<>(1, flag, "取消关注成功");
    }

    /**
     * 根据id查询userInfo
     * @param u
     * @return
     */
    @RequestMapping(value = "/v1/userInfo/id/api", method = RequestMethod.POST)
    public Result<UserInfo> getUserInfoById(@RequestBody UserInfo u) {
        UserInfo userInfo = userService.getUserInfoById(u);
        if(userInfo == null) {
            return new Result<>(1, null, "通讯失败");
        }
        return new Result<>(1, userInfo, "查询成功");
    }

    /**
     * 取得关注数和粉丝数
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/v1/fans/attention/api", method = RequestMethod.POST)
    public Result<AttentionsFans> getUserFansAndAttention(@RequestBody UserInfo userInfo) {
        AttentionsFans attentionsFans = userService.getUserFansAndAttention(userInfo);
        return new Result<>(1, attentionsFans, "查询成功");
    }

    /**
     * 取得粉丝列表
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/v1/fans/list/api", method = RequestMethod.POST)
    public Result<List<UserInfo>> getFansList(@RequestBody UserInfo userInfo) {
        List<UserInfo> userInfoList = userService.getFansList(userInfo);
        return new Result<>(1, userInfoList, "操作成功");
    }

    /**
     * 取得关注列表
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/v1/attentions/list/api", method = RequestMethod.POST)
    public Result<List<UserInfo>> getAttentionsList(@RequestBody UserInfo userInfo) {
        List<UserInfo> userInfoList = userService.getAttentionsList(userInfo);
        return new Result<>(1, userInfoList, "操作成功");
    }

    /**
     * 管理员登录
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/v1/admin/api")
    public Boolean get(JSONObject jsonObject) {
        return true;
    }
}