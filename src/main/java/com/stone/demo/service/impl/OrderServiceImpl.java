package com.stone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.demo.mapper.OrderMapper;
import com.stone.demo.model.OrderDO;
import com.stone.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:27 上午
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     *
     * @return 全部订单
     */
    @Override
    public List<OrderDO> list() {
        return orderMapper.selectList(new QueryWrapper<OrderDO>());
    }

    /**
     *
     * @param openId 用户open_id
     * @return 用户个人订单
     */
    @Override
    public List<OrderDO> findByUserId(String openId) {
        return orderMapper.selectList(new QueryWrapper<OrderDO>().eq("open_id",openId));
    }
}
