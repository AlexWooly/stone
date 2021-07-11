package com.stone.demo.service;

import com.stone.demo.model.MessageImgDO;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 1:36 上午
 * @Version 1.0
 */
public interface MsgImgService {
    List<MessageImgDO> list();

    List<String> findByMessageId(Integer id);

    int deleteById(Integer id);

    int deleteByMessageId(Integer id);
}
