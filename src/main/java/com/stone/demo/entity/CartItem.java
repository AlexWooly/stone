package com.stone.demo.entity;

import lombok.Data;

/**
 * @Author NJUPT wly
 * @Date 2021/7/13 1:10 上午
 * @Version 1.0
 */
@Data
public class CartItem {
    String openId;
    int num;
    int productId;
}
