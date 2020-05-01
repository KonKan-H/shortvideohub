package com.zzh.shortvideohub.util;

import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zzh
 * @date 2020/4/24 13:54
 */
public class Util {

    /**
     * md5加密
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    static public String md5Encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update(password.getBytes());
        String result = new BigInteger(1, md5.digest()).toString(16);
        return result;
    }

}
