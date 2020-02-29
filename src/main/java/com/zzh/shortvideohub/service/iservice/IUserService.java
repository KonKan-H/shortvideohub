package com.zzh.shortvideohub.service.iservice;

import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.User;

import java.security.NoSuchAlgorithmException;

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
    User userLogin(User user);
}
