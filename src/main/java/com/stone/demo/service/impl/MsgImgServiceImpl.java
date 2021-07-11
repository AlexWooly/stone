package com.stone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.demo.mapper.MsgImgMapper;
import com.stone.demo.model.MessageImgDO;
import com.stone.demo.service.MsgImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author NJUPT wly
 * @Date 2021/7/12 1:37 上午
 * @Version 1.0
 */
@Service
public class MsgImgServiceImpl implements MsgImgService {

    @Autowired
    MsgImgMapper msgImgMapper;

    /**
     * 列出所有图片
     * @return
     */
    @Override
    public List<MessageImgDO> list() {
        return msgImgMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 通过信息id查找所有对应图片
     * @param id
     * @return
     */
    @Override
    public List<String> findByMessageId(Integer id) {
        List<MessageImgDO> messageImgDOS = msgImgMapper.selectList(new QueryWrapper<MessageImgDO>().eq("message_id",id));
        assert messageImgDOS != null;
        List<String> list = new ArrayList<>();
        messageImgDOS.forEach(mi->{
            list.add(mi.getUrl());
        });
        return list;
    }

    /**
     * 通过图片id删除图片
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return msgImgMapper.deleteById(id);
    }

    /**
     * 通过消息id来删除对应图片
     * @param id
     * @return
     */
    @Override
    public int deleteByMessageId(Integer id) {
        List<MessageImgDO> messageImgDOS = msgImgMapper.selectList(new QueryWrapper<MessageImgDO>().eq("message_id",id));
        assert messageImgDOS != null;
        List<Integer> list = new ArrayList<>();
        messageImgDOS.forEach(mo->{
            list.add(mo.getId());
        });
        return msgImgMapper.deleteBatchIds(list);
    }
}
