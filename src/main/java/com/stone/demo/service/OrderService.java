package com.stone.demo.service;

import com.stone.demo.model.OrderDO;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:27 上午
 * @Version 1.0
 */
public interface OrderService {
    public List<OrderDO> list();

    public List<OrderDO> findByUserId(String openId);
}
