package com.stone.demo.service;

import com.stone.demo.model.MessageDO;
import com.stone.demo.vo.MessageVO;
import org.w3c.dom.ls.LSInput;

import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/11 11:23 下午
 * @Version 1.0
 */
public interface MessageService {
    int report(MessageDO messageDO);

    int deleteById(Integer id);

    List<MessageVO> list();

    int deleteByBatch(Integer id);
}
