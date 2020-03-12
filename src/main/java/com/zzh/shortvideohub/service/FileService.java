package com.zzh.shortvideohub.service;

import com.zzh.shortvideohub.service.iservice.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zzh
 * @date 2020/3/12 18:22
 */
@Service
@Transactional
@Slf4j
public class FileService implements IFileService {

    @Override
    public String uploadFile(MultipartFile multipartFile){
        //获取前端上传的文件名称
        String multifilename = multipartFile.getOriginalFilename();
        //String uri = request.getSession().getServletContext().getRealPath("/");
        String uri = "D:/files";
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
}
