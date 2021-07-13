package com.stone.demo.model;

import lombok.Data;

/**
 * @Author NJUPT wly
 * @Date 2021/7/13 1:42 下午
 * @Version 1.0
 */
@Data
public class CouponType {
    private Integer id;
    private double minPrice;
    private double discount;
}
