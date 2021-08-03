package com.stone.demo.wechat.tools.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 文件信息表
 *
 * @author zhuhuix
 * @date 2020-04-20
 */
@Data
@TableName("upload_file")
public class UploadFile implements Serializable {

    private Long id;

    /**
     * 文件实际名称
     */
    private String realName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件主名称
     */
    private String primaryName;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 存放路径
     */
    private String path;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 上传人
     */
    private String uploader;

    @JsonIgnore
    private Timestamp createTime;

    public UploadFile(String realName,  String fileName,  String primaryName,  String extension,  String path, String type, Long size, String uploader) {
        this.realName = realName;
        this.fileName = fileName;
        this.primaryName = primaryName;
        this.extension = extension;
        this.path = path;
        this.type = type;
        this.size = size;
        this.uploader = uploader;
    }

    public UploadFile() {

    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "fileName='" + fileName + '\'' +
                ", uploader='" + uploader + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
