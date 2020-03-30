package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.iservice.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.NoSuchAlgorithmException;

import java.util.Date;

/**
 * @author zzh
 * @date 2020/2/28 16:20
 */
@Service
@Slf4j
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    public Integer registerUser(User user) throws NoSuchAlgorithmException {
        //根据手机号查询是否又已经注册
        User u = userMapper.getUserByMP(user);
        if(u != null) {
            return 0;
        }
        user.setCreateTime(new Date());
        String un = user.getMobilePhone();
        user.setUserName(un.substring(0,3) + "****" + un.substring(7, 11));
        userMapper.insertUser(user);
        //设置默认用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setMobilePhone(user.getMobilePhone());
        int row = userMapper.insertUserInfo(user);
        log.info("用户" + user.getMobilePhone() + "于" + new Date() + "注册");
        return 1;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public UserInfo userLogin(User user) {
        //根据用户手机查询对应的user
        User u = userMapper.getUserByMP(user);
        //判断密码是否正确
        if(u != null && u.getPassword().equals(user.getPassword())) {
            //密码正确则查询具体个人信息
            UserInfo userInfo = userMapper.getUserInfo(u);
            User user1 = new User();
            user1.setLastLoginTime(new Date());
            user1.setId(u.getId());
            userMapper.updateUser(user1);
            return userInfo;
        }
        return null;
    }

    /**
     * 用户信息更新
     * @param userInfo
     * @return
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        int row = userMapper.updateUserInfo(userInfo);
        User user = new User();
        return row;
    }

    /**
     * 更新密码
     * @param user
     * @return
     */
    @Override
    public int updatePassword(User user) {
        int row = userMapper.updatePassword(user);
        return row;
    }

    /**
     * 判断观看者是否关注了视频用户
     * @param video
     * @return
     */
    @Override
    public Boolean getAttentionOrNot(Video video) {
        int fansId = video.getLooker();
        int userId = video.getAuthorId();
        if(fansId == userId) {
            return true;
        }
        int num = userMapper.selectAttentionOrNot(userId, fansId);
        return num == 1 ? true : false;
    }

}
