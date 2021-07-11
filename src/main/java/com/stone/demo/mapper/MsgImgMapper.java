package com.stone.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.demo.model.MessageDO;
import com.stone.demo.model.MessageImgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 1:33 上午
 * @Version 1.0
 */
@Mapper
public interface MsgImgMapper extends BaseMapper<MessageImgDO> {
}
