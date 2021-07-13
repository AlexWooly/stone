package com.stone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.demo.entity.CartItem;
import com.stone.demo.mapper.CartItemMapper;
import com.stone.demo.mapper.CartMapper;
import com.stone.demo.mapper.ProductMapper;
import com.stone.demo.model.CartDO;
import com.stone.demo.model.CartItemDO;
import com.stone.demo.model.ProductDO;
import com.stone.demo.vo.CartItemVO;
import com.stone.demo.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 10:27 下午
 * @Version 1.0
 */
@Service
public class CartItemServiceImpl implements com.stone.demo.service.CartItemService {

    @Autowired
    CartItemMapper cartItemMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;


    /**
     * 通过openid获取全部购物车商品
     * @return
     */
    @Override
    public List<CartItemDO> listByOpenId(String openId) {
        Integer cartId = checkCartExist(openId);
        return cartItemMapper.selectList(new QueryWrapper<CartItemDO>().eq("cart_id",cartId));
    }

    /**
     *  加入购物车
     */
    @Override
    public int insert(CartItem cartItem) {
        Integer cartId = checkCartExist(cartItem.getOpenId());
        Integer productId = cartItem.getProductId();
        CartItemDO cartItemDO = cartItemMapper.selectOne(new QueryWrapper<CartItemDO>().eq("cart_id",cartId)
                .eq("product_id",productId));
        if (cartItemDO!= null){
            updateNum(cartItemDO.getId(),cartItemDO.getNum()+cartItem.getNum());
            return 1;
        }
        CartItemDO newCartItemDO = new CartItemDO();
        cartItemDO.setCartId(cartId);
        cartItemDO.setProductId(cartItem.getProductId());
        cartItemDO.setNum(cartItem.getNum());

        return cartItemMapper.insert(newCartItemDO);
    }

    /**
     * 更新购物车商品数量
     * @param cartItemId
     * @param num
     * @return
     */
    @Override
    public int updateNum(int cartItemId, int num) {
        CartItemDO cartItemDO = new CartItemDO();
        cartItemDO.setId(cartItemId);
        cartItemDO.setNum(num);
        return cartItemMapper.updateById(cartItemDO);
    }


    /**
     * 删除购物车商品
     * @param cartItemId 购物车商品id
     * @return
     */
    @Override
    public int delete(String cartItemId) {
        return cartItemMapper.deleteById(cartItemId);
    }

    /**
     * 通过openid获取购物车id
     * @param openId
     * @return
     */
    public Integer checkCartExist(String openId){
        if (cartMapper.selectOne(new QueryWrapper<CartDO>().eq("open_id",openId))==null ||
                cartMapper.selectOne(new QueryWrapper<CartDO>().eq("open_id", openId)).toString().equals("")){
            CartDO cartDO = new CartDO();
            cartDO.setOpenId(openId);
            cartMapper.insert(cartDO);
        }
        return cartMapper.selectOne(new QueryWrapper<CartDO>().eq("open_id",openId)).getId();
    }

    /**
     * cartItem转VO
     * @param cartItemDO
     * @return
     */
    public CartItemVO invertVo(CartItemDO cartItemDO){
        Integer productId = cartItemDO.getProductId();
        ProductDO productDO = productMapper.selectOne(new QueryWrapper<ProductDO>().eq("id",productId));
        assert productDO != null;
        CartItemVO cartItemVO = new CartItemVO();
        cartItemVO.setProductId(productId);
        cartItemVO.setBuyNum(cartItemDO.getNum());
        cartItemVO.setProductTitle(productDO.getName());
        cartItemVO.setProductImg(productDO.getProductImg());
        cartItemVO.setPrice(productDO.getPrice());
        return cartItemVO;
    }

    /**
     * 获取购物车
     * @param openId
     * @return
     */
    @Override
    public CartDO findByOpenId(String openId) {
       Integer CartId =  checkCartExist(openId);
       return cartMapper.selectOne(new QueryWrapper<CartDO>().eq("id",CartId));
    }


}
