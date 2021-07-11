package com.stone.demo.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/11 10:49 下午
 * @Version 1.0
 */
@Data
public class MessageVO {
    private Integer id;
    private String nickname;
    private String role;
    private String avatar;
    private String content;
    private Integer parentId;
    private List<String> imgs;
    private LocalDateTime gmtCreated;
}
