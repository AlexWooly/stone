package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 1:28 上午
 * @Version 1.0
 */
@TableName("message_img")
@Data
public class MessageImgDO {
    private Integer id;
    private String url;
    private Integer enabled;
    private LocalDateTime gmtCreated;
    private String openId;
    private Integer messageId;
}
