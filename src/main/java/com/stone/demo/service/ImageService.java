package com.stone.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@Service
public class ImageService {

    private static final String successpath = "medea/";


//    @Value("${spring.mvc.static-path-pattern}")
//    String requestPath;

    /**
     * 保存文件到本地文件夹
     * @param srcFile
     * @return java.lang.String
     */
    public String saveFileToLocalServeWithDir(MultipartFile srcFile) {
        if (srcFile.isEmpty()) {
            return null;
        }

        //获取源文件名称
        String fileName = srcFile.getOriginalFilename();
        assert fileName != null;
        //获取后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        //随机生成新文件名
        String newName = UUID.randomUUID().toString().replace("-", "") + suffix;

        File dest = new File(new File(successpath).getAbsolutePath() + "/" + newName);

        if (!dest.getParentFile().exists()) {
            if (!dest.getParentFile().mkdirs()) {
                log.error("写入文件时创建父目录异常");
                return null;
            }
        }

        try {
            srcFile.transferTo(dest);
        } catch (IOException e) {
            log.error("文件转存失败");
            e.printStackTrace();
            return null;
        }

        return dest.getAbsolutePath();
    }
}
