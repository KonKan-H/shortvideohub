package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.service.iservice.IUserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

/**
 * @author zzh
 * @date 2020/2/28 16:20
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer registerUser(User user) {
        User u = userMapper.getUserByMP(user);
        if(u != null) {
            return 0;
        }
        user.setCreateTime(new Date());
        String un = user.getMobilePhone();
        user.setUserName(un.substring(0,3) + "****" + un.substring(7, 11));
        int result = userMapper.registerUser(user);
        return 1;
    }

}
