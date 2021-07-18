package com.stone.demo.controller;


import com.stone.demo.model.ProductDO;
import com.stone.demo.service.ProductService;
import com.stone.demo.util.JsonData;
import com.stone.demo.util.JsonUtil;
import com.stone.demo.vo.CartItemVO;
import com.stone.demo.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private ProductService productService;



    @RequestMapping("add")
    public JsonData addCart(@RequestParam("open_id") String openId,@RequestParam("productId") int productId, @RequestParam("buyNum") int buyNum){

        //获取购物车
        BoundHashOperations<String,Object,Object> myCart = getMyCartOps(openId);

        Object cacheObj = myCart.get(productId+"");



        String result ="";
        if (cacheObj!=null){
            result = (String) cacheObj;
        }
        if (cacheObj == null){
            CartItemVO cartItem = new CartItemVO();
            ProductDO productDO = productService.findById(productId);
            cartItem.setBuyNum(buyNum);
            cartItem.setPrice(productDO.getPrice());
            cartItem.setProductImg(productDO.getProductImg());
            cartItem.setProductTitle(productDO.getName());
            cartItem.setProductId(productId);

            myCart.put(productId+"", Objects.requireNonNull(JsonUtil.objectToJson(cartItem)));

        }else {
            CartItemVO cartItemVO = JsonUtil.jsonToPojo(result,CartItemVO.class);
            cartItemVO.setBuyNum(cartItemVO.getBuyNum()+buyNum);
            myCart.put(productId+"", Objects.requireNonNull(JsonUtil.objectToJson(cartItemVO)));
        }
        return JsonData.buildSuccess();

    }



    /**
     * 查看我的购物车
     * @return
     */
    @RequestMapping("mycart")
    public JsonData getMyCart(@RequestParam("openId")String openId){
        BoundHashOperations<String,Object,Object> myCart = getMyCartOps(openId);

        List<Object> itemList = myCart.values();

        List<CartItemVO> cartItemVOList = new ArrayList<>();

        assert itemList != null;
        for (Object i:itemList){
            CartItemVO cartItemVO = JsonUtil.jsonToPojo((String)i,CartItemVO.class);
            cartItemVOList.add(cartItemVO);
        }
        CartVO cartVO = new CartVO();
        cartVO.setCartItems(cartItemVOList);

        return JsonData.buildSuccess(cartVO);
    }

    /**
     * 清空购物车
     * @return
     */
    @RequestMapping("clear")
    public JsonData clear(@RequestParam("openId")String openId){
        String key = getCardKey(openId);

        return JsonData.buildSuccess(redisTemplate.delete(key));
    }

    /**
     * 获取我的购物车id
     * @param openId
     * @return
     */
    private String getCardKey(String openId){
//        int userId =88;
        String cartKey = String.format("video:cart:%s",openId);
        return cartKey;
    }

    /**
     * 抽取购物车
     * @return
     */
    private BoundHashOperations<String,Object,Object> getMyCartOps(String openId){
        String key = getCardKey(openId);
        return redisTemplate.boundHashOps(key);
    }




}
