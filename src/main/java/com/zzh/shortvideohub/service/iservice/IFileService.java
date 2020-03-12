package com.zzh.shortvideohub.service.iservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author zzh
 * @date 2020/3/12 18:22
 */
public interface IFileService {

    String uploadFile(MultipartFile multipartFile) throws IOException;
}
