package com.stone.demo.controller;

import com.stone.demo.entity.CartItem;
import com.stone.demo.model.CartItemDO;
import com.stone.demo.service.CartItemService;
import com.stone.demo.util.JsonData;
import com.stone.demo.vo.CartItemVO;
import com.stone.demo.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 11:24 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/cart/test")
public class TestCartController {

    @Autowired
    CartItemService cartItemService;

    /**
     * 获取我的购物车
     * @param openId
     * @return
     */
    @GetMapping("/list")
    JsonData list(@RequestParam("open_id") String openId){

        List<CartItemDO> cartItemDOS = cartItemService.listByOpenId(openId);
        List<CartItemVO>  cartItemVOS = new ArrayList<>();
        cartItemDOS.forEach(o->{
            cartItemVOS.add(cartItemService.invertVo(o));
        });

        CartVO myCart = new CartVO();
        myCart.setCartItems(cartItemVOS);
        myCart.setTotalAmount(myCart.getTotalAmount());
        return JsonData.buildSuccess(myCart);
    }

    /**
     * 添加到购物车
     * @param cartItem
     * @return
     */
    @PostMapping("/insert")
    JsonData insert(@RequestBody CartItem cartItem){
        return JsonData.buildSuccess(cartItemService.insert(cartItem));
    }


}
