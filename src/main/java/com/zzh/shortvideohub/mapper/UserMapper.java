package com.zzh.shortvideohub.mapper;

import com.zzh.shortvideohub.pojo.User;
import com.zzh.shortvideohub.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
     * @return
     */
    Integer insertUser(User user);

    /**
     * 验证用户
     *
     * @param user
     * @return
     */
    int checkUser(@Param("user") User user);

    /**
     * 创建默认用户信息
     *
     * @param user
     * @return
     */
    int insertUserInfo(@Param("user") User user);

    /**
     * 取得用户具体信息
     *
     * @param user
     * @return
     */
    UserInfo getUserInfo(@Param("user") User user);

    /**
     * 用户信息更新
     *
     * @param userInfo
     * @return
     */
    int updateUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    int updatePassword(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUser(@Param("user") User user);

    /**
     * 判断观看者是否关注了视频用户
     *
     * @param userId
     * @param fansId
     * @return
     */
    int selectAttentionOrNot(@Param("userId") int userId, @Param("fansId") int fansId);
}
