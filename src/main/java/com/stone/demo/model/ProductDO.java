package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author NJUPT wly
 * @Date 2021/7/9 11:44 下午
 * @Version 1.0
 */
@Data
@TableName("product")
public class ProductDO {
    private Integer id;
    private String name;
    private double price;
    private String type;
    private Integer purchasedNum;
    private Integer stock;
    private String productInfo;
    private String productImg;
}
