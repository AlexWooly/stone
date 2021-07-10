package com.stone.demo.controller;

import com.stone.demo.service.ImageService;
import com.stone.demo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 6:43 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    @Autowired
    ImageService imageService;

    @PostMapping("/upload")
    public JsonData upload(@RequestBody MultipartFile srcFile){
        return JsonData.buildSuccess(imageService.saveFileToLocalServeWithDir(srcFile));
    }

}
