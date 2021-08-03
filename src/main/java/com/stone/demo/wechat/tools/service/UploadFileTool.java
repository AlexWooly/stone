package com.stone.demo.wechat.tools.service;

import com.stone.demo.util.JsonData;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口定义
 *
 * @author zhuhuix
 * @date 2020-04-20
 */
public interface UploadFileTool {

    /**
     * 文件上传
     *
     * @param uploader  上传人
     * @param realName  文件实际名称
     * @param multipartFile 文件
     * @return 上传信息
     */
   JsonData upload(String uploader, String realName, MultipartFile multipartFile);
}
