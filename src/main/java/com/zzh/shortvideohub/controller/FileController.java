package com.zzh.shortvideohub.controller;

import com.zzh.shortvideohub.pojo.Result;
import com.zzh.shortvideohub.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zzh
 * @date 2020/3/12 15:36
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传到服务器
     * @return
     */
    @RequestMapping(value = "/v1/file/api", method = RequestMethod.POST)
    public Result<String> uploadFile(@RequestParam(value="file") MultipartFile multipartFile) throws IOException {
        String uri = fileService.uploadFile(multipartFile);
        Result<String> result = new Result<String>(1, uri, "头像上传成功");
        return result;
    }
}
