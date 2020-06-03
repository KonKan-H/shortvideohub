package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.mapper.VideoMapper;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.iservice.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Value("${fileUrl.videoFileUrl}")
    private String videoFileUrl;

    @Value("${fileUrl.coverFileUrl}")
    private String coverFileUrl;

    @Value("${fileUrl.avatarFileUrl}")
    private String avatarFileUrl;

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
        String uri = avatarFileUrl;
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
    public Map<String, String> uploadVideo(MultipartFile videoMultipartFile, MultipartFile coverMultipartFile, String description, String userId) throws IOException {
        String videoName = videoMultipartFile.getOriginalFilename();
        String videoUrl = videoFileUrl + videoName;
        String coverName = coverMultipartFile.getOriginalFilename();
        String coverUrl = coverFileUrl + coverName;
        File videoFile = new File(videoUrl);
        File coverFile = new File(coverUrl);
        try {
            videoMultipartFile.transferTo(videoFile);
            coverMultipartFile.transferTo(coverFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String m3u8Name = MP42M3U8(videoUrl);
        Video video = buildVideo(m3u8Name, coverName, description, userId);
        videoMapper.insertVideo(video);
        Map<String, String> map = new HashMap<>();
        map.put("videoUrl" , videoUrl);
        map.put("coverUrl" , coverUrl);
        return map;
    }

    /**
     * 上传视频 不带封面
     * @param videoMultipartFile
     * @param description
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> uploadVideo2(MultipartFile videoMultipartFile, String description, String userId) throws IOException {
        String videoName = videoMultipartFile.getOriginalFilename();
        String videoUrl = videoFileUrl + videoName;
        File videoFile = new File(videoUrl);
        videoMultipartFile.transferTo(videoFile);
        String m3u8Name = MP42M3U8(videoUrl);
        String coverName = getVideoCover(videoUrl);
        Video video = buildVideo(m3u8Name, coverName, description, userId);
        videoMapper.insertVideo(video);
        Map<String, String> map = new HashMap<>();
        map.put("videoUrl" , videoUrl);
        map.put("coverUrl" , coverFileUrl + coverName);
        return map;
    }

    private Video buildVideo(String videoName, String coverName, String description, String userId) {
        Video video = new Video();
        video.setUrl(videoName);
        video.setCover(coverName);
        video.setDescription(description);
        video.setAuthorId(Integer.valueOf(userId));
        video.setCreateTime(new Date());
        video.setLikes(0);
        video.setDownloads(0);
        video.setComments(0);
        //0为审核通过，1为审核不通过，2为审核中
        video.setStatus(2);
        return video;
    }

    public String MP42M3U8(String videoUrl) {
        String m3u8NamePrefix = videoUrl.substring(videoUrl.lastIndexOf("/") + 1);
        String m3u8Name = m3u8NamePrefix.substring(0, m3u8NamePrefix.indexOf(".")) + ".m3u8";
        String ml = "ffmpeg -i " + videoUrl +
                " -vcodec copy -acodec copy -hls_list_size 0 -vbsf h264_mp4toannexb " + videoFileUrl + m3u8Name;
        try {
            Process p = Runtime.getRuntime().exec(ml);
        } catch (IOException e1) {
            log.info("切片失败: " + ml);
            e1.printStackTrace();
        }
        log.info("切片成功: " + ml);
        return m3u8Name;
    }

    public String getVideoCover(String videoUrl) {
        String coverName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
        String ml = "ffmpeg -i " + videoUrl + " " + coverFileUrl + coverName;
        try {
            Process p = Runtime.getRuntime().exec(ml);
        } catch (IOException e1) {
            log.info("切取失败: " + ml);
            e1.printStackTrace();
        }
        log.info("切取封面成功: " + ml);
        return coverName;
    }

    public String others2MP4() {
        return null;
    }

}
