package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/11 10:44 下午
 * @Version 1.0
 */
@Data
@TableName("message")
public class MessageDO {
    private Integer id;
    private String open_id;
    private String tag;
    private String content;
    private Integer enabled;
    private Integer parentId;
    private LocalDateTime gmtCreated;
}
