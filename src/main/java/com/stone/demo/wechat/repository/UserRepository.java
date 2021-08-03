package com.stone.demo.wechat.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.demo.wechat.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author NJUPT wly
 * @Date 2021/8/3 7:32 下午
 * @Version 1.0
 */
@Mapper
public interface UserRepository extends BaseMapper<User> {
}
