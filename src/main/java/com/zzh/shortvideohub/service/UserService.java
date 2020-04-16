package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.*;
import com.zzh.shortvideohub.service.iservice.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.NoSuchAlgorithmException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        userInfo.setSex("男");
        userInfo.setFans(0);
        userInfo.setAttentions(0);
        userInfo.setAge(18);
        int row = userMapper.insertUserInfo(userInfo);
        log.info("用户" + user.getMobilePhone() + "于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") + "注册");
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
     * @param attention
     * @return
     */
    @Override
    public Boolean getAttentionOrNot(Attention attention) {
        if(attention.getFansId() == attention.getUserId()) {
            return true;
        }
        int num = userMapper.selectAttentionOrNot(attention);
        return num == 1 ? true : false;
    }

    /**
     * 根据id查询userInfo
     * @param u
     * @return
     */
    @Override
    public UserInfo getUserInfoById(UserInfo u) {
        UserInfo userInfo = userMapper.selectUserInfoById(u.getUserId());
        return userInfo;
    }

    /**
     * 关注
     * @param attention
     * @return
     */
    @Override
    public Boolean attentionUser(Attention attention) {
        attention.setNow(new Date());
        int row = userMapper.attentionUser(attention);
        //增加粉丝数
        userMapper.insertFansNum(attention);
        //增加关注数
        userMapper.insertAttentionsNum(attention);
        return row == 1 ? true : false;
    }

    /**
     * 取消关注
     * @param attention
     * @return
     */
    @Override
    public Boolean cancelAttentionUser(Attention attention) {
        int row = userMapper.cancelAttentionUser(attention);
        //减少粉丝数
        userMapper.decreaseFansNum(attention);
        //减少关注数
        userMapper.decreaseAttentionNum(attention);
        return row == 1 ? true : false;
    }

    /**
     * 取得粉丝列表
     * @param userInfo
     * @return
     */
    @Override
    public List<UserInfo> getFansList(UserInfo userInfo) {
        List<UserInfo> userInfoList = userMapper.getFansList(userInfo);
        return userInfoList;
    }

    /**
     * 取得关注列表
     * @param userInfo
     * @return
     */
    @Override
    public List<UserInfo> getAttentionsList(UserInfo userInfo) {
        List<UserInfo> userInfoList = userMapper.getAttentionsList(userInfo);
        return userInfoList;
    }

    /**
     * 取得关注数和粉丝数
     * @param userInfo
     * @return
     */
    @Override
    public AttentionsFans getUserFansAndAttention(UserInfo userInfo) {
        AttentionsFans attentionFans = userMapper.getUserFansAndAttention(userInfo);
        return attentionFans;
    }

}
