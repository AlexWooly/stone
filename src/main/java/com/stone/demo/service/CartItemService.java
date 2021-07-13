package com.stone.demo.service;

import com.stone.demo.entity.CartItem;
import com.stone.demo.model.CartDO;
import com.stone.demo.model.CartItemDO;
import com.stone.demo.vo.CartItemVO;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 10:14 下午
 * @Version 1.0
 */
public interface CartItemService {
    List<CartItemDO> listByOpenId(String openId);

    int insert(CartItem cartItem);

    int updateNum(int cartItemId,int num);

    int delete(String cartItemId);

    CartItemVO invertVo(CartItemDO cartItemDO);

    CartDO findByOpenId(String openId);

//    List<CartItemDO> listByName(String openId,String name);
}
