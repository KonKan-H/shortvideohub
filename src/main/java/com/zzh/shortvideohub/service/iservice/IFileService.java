package com.zzh.shortvideohub.service.iservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


/**
 * @author zzh
 * @date 2020/3/12 18:22
 */
public interface IFileService {

    /**
     * 上传文件
     * @param multipartFile
     * @return
     * @throws IOException
     */
    String uploadFile(MultipartFile multipartFile) throws IOException;

    /**
     * 上传视频
     * @param videoFile
     * @param coverFile
     * @param description
     * @return
     */
    Map<String, String> uploadVideo(MultipartFile videoFile, MultipartFile coverFile, String description, String userId);
}
