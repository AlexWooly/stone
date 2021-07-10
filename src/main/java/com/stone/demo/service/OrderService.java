package com.stone.demo.service;

import com.stone.demo.entity.Order;
import com.stone.demo.model.OrderDO;
import com.stone.demo.model.ProductDO;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:27 上午
 * @Version 1.0
 */
public interface OrderService {
    public List<OrderDO> list();

    public List<OrderDO> findByUserId(String openId);

    public int paySuccess(String orderId);

    public List<String> makeOrder(Order order);
}
