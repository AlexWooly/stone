package com.stone.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 12:03 下午
 * @Version 1.0
 */
@Data
@TableName("cart")
public class CartDO {
    private Integer id;
    private String openId;
}
