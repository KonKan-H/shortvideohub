package com.zzh.shortvideohub.controller;

import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;

/**
 * @author zzh
 * @date 2020/2/27 15:10
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/registration/api", method = RequestMethod.POST)
    private Result<Integer> registration(@RequestBody User user) throws NoSuchAlgorithmException {
        Integer row = userService.registerUser(user);
        if(row == 1) {
            return new Result<Integer>(1, 1, "注册成功，返回登录页面登录");
        }
        return new Result<Integer>(1, 0, "该手机号已经被注册");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/v1/login/api", method = RequestMethod.POST)
    private Result<UserInfo> userLogin(@RequestBody User user) {
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
    private Result<Integer> updateUsserInfo(@RequestBody UserInfo userInfo) {
//        int row = userService.updateUserInfo(userInfo);
        int row = 0;
        if(row == 1) {
            return new Result<Integer>(1, row, "更新成功");
        }
        return new Result<Integer>(-1, row, "更新失败");
    }
}
