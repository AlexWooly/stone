package com.stone.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.demo.model.Problem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 9:35 下午
 * @Version 1.0
 */
@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {
}
