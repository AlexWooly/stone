package com.stone.demo.entity;

import com.stone.demo.model.OrderDO;
import lombok.Data;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:33 下午
 * @Version 1.0
 */
@Data
public class Orders {
    private List<OrderDO> orderDOS;
    private Integer price;
    private Integer finalPrice;
    private Integer discount;
    private Orders(List<OrderDO> orderDOS){

    }
}
