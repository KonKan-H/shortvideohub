package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.ConstantCache.ConstantCache;
import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.UserInfo;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.iservice.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzh
 * @date 2020/3/12 18:22
 */
@Service
@Transactional
@Slf4j
public class FileService implements IFileService {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 上传视频
     * @param multipartFile
     * @return
     */
    @Override
    public String uploadFile(MultipartFile multipartFile){
        //获取前端上传的文件名称
        String multifilename = multipartFile.getOriginalFilename();
        //String uri = request.getSession().getServletContext().getRealPath("/");
        String uri = ConstantCache.AVATAR_FILE_URI;
        String url = uri+ "/" +  multifilename;
        //在项目新建一个 你重新生成名称的文件
        File file = new File(url);
        //将接收的到的 multipartFile 类型的文件 转为 file
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 上传视频
     * @param videoMultipartFile
     * @param coverMultipartFile
     * @param description
     * @return
     */
    @Override
    public Map<String, String> uploadVideo(MultipartFile videoMultipartFile, MultipartFile coverMultipartFile, String description, String userId) {
        String videoName = videoMultipartFile.getOriginalFilename();
        String coverName = coverMultipartFile.getOriginalFilename();
        String videoUrl = ConstantCache.VIDEO_FILE_URI + videoName;
        String coverUrl = ConstantCache.COVER_FILE_URI + coverName;
        Video video = new Video();
        video.setUrl(videoName);
        video.setCover(coverName);
        video.setDescription(description);
        video.setAuthorId(Integer.valueOf(userId));
        video.setCreateTime(new Date());
        //videoMapper.insertVideo(video);
        File videoFile = new File(videoUrl);
        File coverFile = new File(coverUrl);
        try {
            videoMultipartFile.transferTo(videoFile);
            coverMultipartFile.transferTo(coverFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        map.put("videoUrl" , videoUrl);
        map.put("coverUrl" , coverUrl);
        return map;
    }
}
