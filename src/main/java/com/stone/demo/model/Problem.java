package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 9:23 下午
 * @Version 1.0
 */
@TableName("problem")
@Data
public class Problem {
    private Integer id;
    private String openId;
    private String detail;
    private String type;
    private LocalDateTime gmtCreated;
}
