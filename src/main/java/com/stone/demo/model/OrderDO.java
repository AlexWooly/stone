package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.*;
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

    @TableId(type = IdType.INPUT)
    private Long id;

    private String openId;
    private Integer productId;
    private Integer num;
    private double totalPrice;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreated;
    private String status;
//    private Integer enabled;


}
