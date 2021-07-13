package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 9:19 下午
 * @Version 1.0
 */
@TableName("work_submit")
@Data
public class WorkSubmit {
    private Integer id;
    private String title;
    private String open_id;
    private String detail;
    private Integer phone;
    private LocalDateTime gmtCreated;
    private String path;
}
