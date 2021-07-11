package com.stone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.demo.entity.Order;
import com.stone.demo.entity.OrderStatus;
import com.stone.demo.mapper.OrderMapper;
import com.stone.demo.mapper.ProductMapper;
import com.stone.demo.model.OrderDO;
import com.stone.demo.model.ProductDO;
import com.stone.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:27 上午
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    ProductMapper productMapper;

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

    /**
     * 更新订单状态
     * @param orderId
     * @return
     */
    public int paySuccess(String orderId){
        assert orderId !=null;
        assert !orderId.equals("");
        OrderDO orderDO = new OrderDO();
        orderDO.setStatus(OrderStatus.PAYED.toString());
        return orderMapper.update(orderDO,new QueryWrapper<OrderDO>().eq("id",Integer.valueOf(orderId)));
    }


    /**
     * 创建订单订单
     * @param order
     * @return
     */
    @Override
    public List<String>  makeOrder(Order order) {
        List<Map<String,Integer>> list = order.getList();
        List<String> msg = new ArrayList<>();
        list.forEach(m->{
            int num = m.get("num");
            int productId = m.get("productId");
            double price = 0 ;
            //获取分布式锁
            RLock transferLock = redissonClient.getLock("PURCHASE");

            transferLock.lock();
            //业务逻辑卸载try...catch中 ，finally最后一定要释放锁
            try {
                //尝试获取锁
                ProductDO productDO = productMapper.selectById(m.get("productId"));
                assert productDO !=null;
                price = productDO.getPrice();

                if (productDO.getStock() < 1) {
                    msg.add(productDO.getName()+"库存不够了");
                    return;
                }
                if (productDO.getStock()-num < 0) {
                    msg.add(productDO.getName()+"超过最大库存");
                    return;
                }
                productDO.setStock(productDO.getStock() - num);
                productMapper.updateById(productDO);

//                //格式化格式为年月日
//                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//                //获取当前时间
//                String now = LocalDate.now().format(dateTimeFormatter);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
                String now = formatter.format(LocalDateTime.now());
                //通过redis的自增获取序号
                RAtomicLong atomicLong = redissonClient.getAtomicLong(now);
                atomicLong.expire(1, TimeUnit.DAYS);
                String s = now + atomicLong.incrementAndGet();
                Long id = Long.valueOf(s);

                OrderDO orderDO = new OrderDO();
                orderDO.setId(id);
                orderDO.setOpenId("wly");
                orderDO.setNum(num);
                orderDO.setProductId(productId);
                double totalPrice = num*price;
                orderDO.setTotalPrice(totalPrice);
                orderDO.setStatus(OrderStatus.PAYING.toString());
                //TODO 优惠券
                orderMapper.insert(orderDO);
                msg.add(id.toString());

            } catch (Exception e) {
                log.error("",e);
                msg.add(e.toString());
            } finally {
                // 无论是否出现异常，一定解锁
                transferLock.unlock();
            }
        });
        return msg;
    }

}
