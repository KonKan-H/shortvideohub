package com.zzh.shortvideohub.test;

import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.constantCache.ConstantCache;
import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.Admin;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.service.UserService;
import com.zzh.shortvideohub.service.VideoService;
import com.zzh.shortvideohub.util.RedisService;
import com.zzh.shortvideohub.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author zzh
 * @date 2020/2/29 16:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Value("${fileUrl.videoFileUrl}")
    private String videoFileUrl;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @Test
    public void md5() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
//        md5.update("123456".getBytes());
//        String result = new BigInteger(1, md5.digest()).toString(16);
        String result = Util.md5Encryption("admin");
        System.out.println(result);
    }

    @Test
    public void yunSQL() {
        User user = new User();
        user.setMobilePhone("13967574627");
        System.out.println(JSONObject.toJSONString(userMapper.getUserByMP(user)));
    }

    @Test
    public void redis() {
        System.out.println(redisService.exists("test"));
    }

    @Test
    public void cover() {
        String coverName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
        try {
            Process p = Runtime.getRuntime().exec(new String[] {
                    "ffmpeg", "-i", videoFileUrl + "d7b0ca30-7a3e-11ea-af2e-715437bd057b.mp4",
                    videoFileUrl + coverName
            });
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Test
    public void video() {
        String m3u8Name = UUID.randomUUID().toString().replace("-", "") + ".m3u8";
        String ml = "ffmpeg -i " + videoFileUrl + "d7b0ca30-7a3e-11ea-af2e-715437bd057b.mp4" +
                " -vcodec copy -acodec copy -hls_list_size 0 -vbsf h264_mp4toannexb " + videoFileUrl + m3u8Name;
        System.out.println(ml);
        try {
            Process p = Runtime.getRuntime().exec(ml);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Test
    public void key() {
        String key = ConstantCache.LIKE_COMMENT_LABEL(1, 2);
        System.out.println(key);
    }

    @Test
    public void value() {
        System.out.println(videoFileUrl);
    }

    @Test
    public void faTask() {
        userService.faNumSyncTask();
    }

    @Test
    public void lcTask() {
        videoService.videoLCNumUpdate();
    }

    @Test
    public void hotVideo() {
        videoService.videoHotSync();
    }

    @Test
    public void json() {
        Admin admin = JSONObject.toJavaObject(JSONObject.parseObject(redisService.get("b6a54ffdcc8dfa88a3eb6aa74d60bc3c").toString()), Admin.class);
        System.out.println();
    }

    @Test
    public void test() {
        Integer i01 = 59;
        int i02 = 59;
        Integer i03 =Integer.valueOf(59);
        Integer i04 = new Integer(59);
        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i03 == i04);
        System.out.println(i04 == i02);
    }

    @Test
    public void t2() {
        UserInfo userInfo = JSONObject.parseObject(JSONObject.parse(JSONObject.toJSONString(redisService.get("74"))).toString(), UserInfo.class);
        System.out.println(JSONObject.toJSON(userInfo));
    }
}