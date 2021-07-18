package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime gmtCreated;
    private String openId;
    private Integer messageId;
}
