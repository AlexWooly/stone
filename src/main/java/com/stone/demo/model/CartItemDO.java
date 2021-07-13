package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 12:03 下午
 * @Version 1.0
 */
@Data
@TableName("cart_item")
public class CartItemDO {
    private Integer id;
    private Integer productId;
    private Integer cartId;
    private Integer num;
    private Integer enabled;
}
