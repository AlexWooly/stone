package com.stone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.demo.mapper.ProductMapper;
import com.stone.demo.model.ProductDO;
import com.stone.demo.service.ProductService;
import com.stone.demo.vo.CartItemVO;
import com.stone.demo.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 12:07 上午
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    /**
     *
     * @return 全部商品
     */
    @Override
    public List<ProductDO> list() {
        return productMapper.selectList(new QueryWrapper<ProductDO>());
    }


    /**
     *
     * @param name 用户查找输入
     * @return 模糊匹配商品
     */
    @Override
    public List<ProductDO> findByName(String name) {
        return productMapper.selectList(new QueryWrapper<ProductDO>().like("name",name));
    }

    /**
     * 获取所有产品种类
     * @return
     */
    @Override
    public Set<String> findAllType() {
        List<ProductDO> productDOS = productMapper.selectList(new QueryWrapper<ProductDO>().select("type"));
        Set<String> set = new HashSet<>();
        productDOS.forEach(productDO -> set.add(productDO.getType()));
        return set;
    }

    /**
     * 放入购物车
     * @param cartItemVO
     * @return
     */
    @Override
    public int insertCart(CartItemVO cartItemVO) {
        try {
            getMyCart().getCartItems().add(cartItemVO);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public CartVO getMyCart(){
        return new CartVO();
    }


}
