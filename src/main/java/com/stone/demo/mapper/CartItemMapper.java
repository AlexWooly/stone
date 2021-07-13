package com.stone.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.demo.model.CartItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 10:14 下午
 * @Version 1.0
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItemDO> {

}
