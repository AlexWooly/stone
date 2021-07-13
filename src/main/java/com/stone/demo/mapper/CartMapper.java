package com.stone.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.demo.model.CartDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 10:47 下午
 * @Version 1.0
 */
@Mapper
public  interface CartMapper extends BaseMapper<CartDO> {
}
