package com.zzh.shortvideohub.controller;

import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.User;
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
            return new Result<Integer>(1, 1, "注册成功，返回注册页面注册");
        }
        return new Result<Integer>(1, 0, "该手机号已经被注册");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/v1/login/api", method = RequestMethod.POST)
    private Result<User> userLogin(@RequestBody User user) {
        User u = userService.userLogin(user);
        if(u == null) {
            return new Result<User>(-1, null, "用户名或密码错误");
        }
        return new Result<User>(1, u, "登录成功");
    }
}
