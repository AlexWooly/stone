package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:19 上午
 * @Version 1.0
 */
@TableName("order_p")
@Data
public class OrderDO {
    private String id;
    private String openId;
    private Integer productId;
    private Integer num;
    private double totalPrice;
    private LocalDateTime gmtCreated;
    private Integer enabled;

}
