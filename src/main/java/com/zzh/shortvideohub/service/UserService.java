package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.service.iservice.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

/**
 * @author zzh
 * @date 2020/2/28 16:20
 */
@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer registerUser(User user) throws NoSuchAlgorithmException {
        User u = userMapper.getUserByMP(user);
        if(u != null) {
            return 0;
        }
        user.setCreateTime(new Date());
        String un = user.getMobilePhone();
        user.setUserName(un.substring(0,3) + "****" + un.substring(7, 11));
        u = userMapper.registerUser(user);
        log.info("用户" + user.getMobilePhone() + "于" + new Date() + "注册");
        return 1;
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        User u = userMapper.getUserByMP(user);
        if(u != null && u.getMobilePhone().equals(user.getMobilePhone()) && u.getPassword().equals(user.getPassword())) {
            return u;
        }
        return null;
    }
}
