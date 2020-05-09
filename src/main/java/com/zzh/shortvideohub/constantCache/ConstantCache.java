package com.zzh.shortvideohub.constantCache;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/8 16:32E
 */
public class ConstantCache {
    //文件存储位置
//    public static String FILE_URL = "D:/sv_files/";
//    public static String FILE_URL = "/var/opt/";
//    public static String COVER_FILE_URI = FILE_URL + "cover/";
//    public static String VIDEO_FILE_URI = FILE_URL + "video/";
//    public static String AVATAR_FILE_URI = FILE_URL + "avatar/";

//    public static String VIDEO_URL = "http://112.126.82.137:8080/video/";
//    public static String COVER_URL = "http://112.126.82.137:8080/cover/";

    public static String INIT_AVATAR = "ic_avatar_default.png";

    //点赞评论
    public static String LIKE_COMMENT_LABEL(int replyId, int userId) {
        String label = "SOMEBODY_LIKE_COMMENT_" + userId + "_" + replyId;
        return label;
    }
}
