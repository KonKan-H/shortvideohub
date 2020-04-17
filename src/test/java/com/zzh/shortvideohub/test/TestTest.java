package com.zzh.shortvideohub.test;

import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.ConstantCache.ConstantCache;
import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.service.UserService;
import com.zzh.shortvideohub.util.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @Test
    public void md5() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update("123456".getBytes());
        String result = new BigInteger(1, md5.digest()).toString(16);
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

}