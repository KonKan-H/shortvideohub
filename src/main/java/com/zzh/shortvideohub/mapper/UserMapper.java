package com.zzh.shortvideohub.mapper;

import com.zzh.shortvideohub.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zzh
 * @date 2020/2/28 16:32
 */
@Repository
public interface UserMapper {

    /**
     * 根据手机号查询用户
     *
     * @param user
     * @return
     */
    User getUserByMP(@Param("user") User user);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    User registerUser(@Param("user") User user);

    /**
     * 验证用户
     *
     * @param user
     * @return
     */
    int checkUser(@Param("user") User user);
}
