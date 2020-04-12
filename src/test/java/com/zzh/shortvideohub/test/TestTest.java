package com.zzh.shortvideohub.test;

import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.mapper.UserMapper;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.service.UserService;
import com.zzh.shortvideohub.util.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        System.out.println(redisService.get("test"));
    }

}