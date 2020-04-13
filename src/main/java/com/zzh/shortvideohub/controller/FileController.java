package com.zzh.shortvideohub.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.pojo.Video;
import com.zzh.shortvideohub.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author zzh
 * @date 2020/3/12 15:36
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     * @return
     */
    @RequestMapping(value = "/v1/file/api", method = RequestMethod.POST)
    public Result<String> uploadFile(@RequestParam(value="file") MultipartFile multipartFile) throws IOException {
        String uri = fileService.uploadFile(multipartFile);
        Result<String> result = new Result<String>(1, uri, "上传成功");
        return result;
    }

    /**
     * 上传视频带封面
     * @param
     * @return
     */
    @RequestMapping(value = "/v1/videoInfo/cover/api", method = RequestMethod.POST)
    public Result<Map> uploadVideoInfo(@RequestParam(value="video") MultipartFile videoFile,
                                          @RequestParam(value="cover") MultipartFile coverFile,
                                          @RequestParam(value = "description") String description,
                                          @RequestParam(value = "userId") String userId) throws IOException {
        Map<String, String> map = fileService.uploadVideo(videoFile, coverFile, description, userId);
        return new Result<>(1, map, "操作成功");
    }

    /**
     * 上传视频 不带封面
     * @param
     * @return
     */
    @RequestMapping(value = "/v1/videoInfo/api", method = RequestMethod.POST)
    public Result<Map> uploadVideoInfo2(@RequestParam(value="video") MultipartFile videoFile,
                                       @RequestParam(value = "description") String description,
                                       @RequestParam(value = "userId") String userId) throws IOException {
        Map<String, String> map = fileService.uploadVideo2(videoFile, description, userId);
        return new Result<>(1, map, "操作成功");
    }
}
