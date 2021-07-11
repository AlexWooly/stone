package com.stone.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.demo.model.MessageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author NJUPT wly
 * @Date 2021/7/11 11:21 下午
 * @Version 1.0
 */
@Mapper
public interface MessageMapper extends BaseMapper<MessageDO> {
}
