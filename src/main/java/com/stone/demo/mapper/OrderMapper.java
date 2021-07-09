package com.stone.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.demo.model.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 1:26 上午
 * @Version 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {
}
