package com.stone.demo.controller;

import com.stone.demo.entity.Order;
import com.stone.demo.service.OrderService;
import com.stone.demo.service.ProductService;
import com.stone.demo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:30 上午
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 获取全部订单列表
     * @return 全部订单列表
     */
    @GetMapping("/list")
    public JsonData list(){
        return JsonData.buildSuccess(orderService.list());
    }

    /**
     * 获取用户个人订单列表
     * @param openId 用户open_id
     * @return 用户个人订单列表
     */
    @GetMapping("/get_personal_order")
    public JsonData getPersonalOrder(@RequestParam("openId")String openId){
        return JsonData.buildSuccess(orderService.findByUserId(openId));
    }

    /**
     * 更新订单状态（支付成功）
     * @param openIds 需要更新的订单
     */
    @GetMapping("/pay_success")
    public JsonData paySuccess(@RequestParam("order_ids")List<String> openIds){
        openIds.forEach(o->orderService.paySuccess(o));
        return JsonData.buildSuccess("订单支付成功");
    }

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/make_order")
    public JsonData makeOrder(@RequestBody Order order){
        return JsonData.buildSuccess(orderService.makeOrder(order));
    }

}
