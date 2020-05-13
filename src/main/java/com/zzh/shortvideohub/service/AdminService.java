package com.zzh.shortvideohub.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzh.shortvideohub.constantCache.ConstantCache;
import com.zzh.shortvideohub.mapper.AdminMapper;
import com.zzh.shortvideohub.pojo.*;
import com.zzh.shortvideohub.service.iservice.IAdminService;
import com.zzh.shortvideohub.util.RedisService;
import com.zzh.shortvideohub.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zzh
 * @date 2020/4/24 13:40
 */
@Service
@Slf4j
@Transactional
public class AdminService implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisService redisService;

    @Value("${initData.password}")
    private String initPassword;

    @Value("${VideoFileUrl}")
    private String VIDEO_URL;

    @Value("${CoverFileUrl}")
    private String COVER_URL;

    @Override
    public Admin adminLogin(Admin admin) throws NoSuchAlgorithmException {
        String passwordMD5 = Util.md5Encryption(admin.getPassword());
        admin.setPassword(passwordMD5);
        Admin admin1 = adminMapper.checkAdmin(admin);
        if(admin1 != null) {
            admin1.setAccessToken(loginSuccess(admin1));
        }
        return admin1;
    }

    /**
     * 取得用户列表
     * @return
     */
    @Override
    public PageInfo<UserInfo> getUserList(UserInfo userInfo) {
        PageHelper.startPage(userInfo.getCurrentPage(), userInfo.getPageSize());
        List<UserInfo> userInfoList = adminMapper.getUserList(userInfo);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        return pageInfo;
    }

    /**
     * 取得视频列表
     * @param v
     * @return
     */
    @Override
    public PageInfo<Video> getVideoList(Video v) {
        PageHelper.startPage(v.getCurrentPage(), v.getPageSize());
        List<Video> videoList = adminMapper.getVideoList(v);
        setVideoParam(videoList);
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        return pageInfo;
    }

    /**
     * 查询待审核视频
     * @param video
     * @return
     */
    @Override
    public PageInfo<Video> getCheckVideo(Video video) {
        PageHelper.startPage(video.getCurrentPage(), video.getPageSize());
        List<Video> videoList = adminMapper.getCheckVideo(video);
        setVideoParam(videoList);
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        return pageInfo;
    }

    /**
     * 更改用户登录权限
     * @param userInfo
     * @return
     */
    @Override
    public int changeUserStatus(UserInfo userInfo) {
        int row = adminMapper.changeUserStatus(userInfo);
        if(redisService.exists(userInfo.getUserId().toString())) {
            UserInfo u = JSONObject.parseObject(JSONObject.parse(JSONObject.toJSONString(redisService.get(userInfo.getUserId().toString()))).toString(), UserInfo.class);
            redisService.remove(u.getUserId().toString());
            redisService.remove(u.getAccessToken());
        }
        return row;
    }

    /**
     * 更改视频权限
     * @param video
     * @return
     */
    @Override
    public int changeVideoStatus(Video video) {
        int row = adminMapper.changeVideoStatus(video);
        return row;
    }

    /**
     * 重置密码
     * @param userInfo
     * @return
     */
    @Override
    public boolean resetPass(UserInfo userInfo) throws NoSuchAlgorithmException {
        User user = new User();
        user.setId(userInfo.getUserId());
        user.setPassword(Util.md5Encryption(initPassword));
        int row = adminMapper.resetPass(user);
        return row == 1;
    }

    /**
     * 删除用户账号
     * @param userInfo
     * @return
     */
    @Override
    public boolean deleteUser(UserInfo userInfo) {
        try {
            adminMapper.deleteUser(userInfo);
            adminMapper.deleteUserInfo(userInfo);
            adminMapper.deleteUserVideo(userInfo);
            adminMapper.deleteUserAttentions(userInfo);
            adminMapper.deleteUserFans(userInfo);
            adminMapper.deleteUserReply(userInfo);
            adminMapper.deleteUserVideoLike(userInfo);
            redisService.remove(userInfo.getUserId().toString());
            if(redisService.exists(userInfo.getUserId().toString())) {
                UserInfo u = JSONObject.parseObject(JSONObject.parse(JSONObject.toJSONString(redisService.get(userInfo.getUserId().toString()))).toString(), UserInfo.class);
                redisService.remove(u.getUserId().toString());
                redisService.remove(u.getAccessToken());
            }
        }catch (Exception e) {
            log.info(e.toString());
            return false;
        }
        return true;
    }

    private void setVideoParam(List<Video> videoList) {
        for(Video video : videoList) {
            video.setCover(COVER_URL + video.getCover());
            String url = video.getUrl();
            url = url.substring(0, url.lastIndexOf(".")) + ".mp4";
            video.setUrl(VIDEO_URL + url);
        }
    }

    /**
     * 用户登录成功
     * @param admin
     * @return
     */
    private String loginSuccess(Admin admin) {
        String loginToken = "";
        if(!redisService.exists("ADMIN_" + admin.getId().toString())) {
            loginToken += DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
            //登录缓存
            admin.setAccessToken(loginToken);
            redisService.set(loginToken, JSONObject.toJSONString(admin));
            redisService.set("ADMIN_" + admin.getId().toString(), JSONObject.toJSONString(admin), 1L, TimeUnit.DAYS);
        } else {
            Admin a = JSONObject.parseObject(JSONObject.parse(JSONObject.toJSONString(redisService.get("ADMIN_" + admin.getId().toString()))).toString(), Admin.class);
            loginToken = a.getAccessToken();
        }
        return loginToken;
    }
}
