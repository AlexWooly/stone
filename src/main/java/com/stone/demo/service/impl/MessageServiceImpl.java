package com.stone.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.demo.mapper.MessageMapper;
import com.stone.demo.mapper.MsgImgMapper;
import com.stone.demo.model.MessageDO;
import com.stone.demo.service.MessageService;
import com.stone.demo.service.MsgImgService;
import com.stone.demo.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author NJUPT wly
 * @Date 2021/7/11 11:23 下午
 * @Version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    MsgImgMapper msgImgMapper;

    @Autowired
    MsgImgService msgImgService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 发表消息
     * @param messageDO
     * @return
     */
    @Override
    public int report(MessageDO messageDO) {
        return messageMapper.insert(messageDO);
    }

    /**
     * 通过消息id删除
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = {"message"},key = "#root.args[0]")
    public int deleteById(Integer id) {

        return messageMapper.deleteById(id);
    }

    /**
     * 列出所有消息VO
     * @return
     */
    @Override
//    @Cacheable(value = "message",keyGenerator = "springCacheCustomKeyGenerator",cacheManager = "cacheManager1Hour")
    public List<MessageVO> list() {
        List<MessageVO> list = (List<MessageVO>)redisTemplate.opsForValue().get("msg:list");
        if (list != null)
        {
            return list;
        }else {
            List<MessageDO> msgs = messageMapper.selectList(new QueryWrapper<MessageDO>());
            List<MessageVO> list_new = new ArrayList<>();
            msgs.forEach(msg->{
                list_new.add(invert(msg));
            });
            //redis缓存，避免缓存穿透
            redisTemplate.opsForValue().set("msg:list",list_new,60, TimeUnit.SECONDS);
            return list_new;
        }

    }

    /**
     * 通过id批量删除消息（子消息连同父消息一起删除）
     * @param id
     * @return
     */
    @Override
    public int deleteByBatch(Integer id) {
        QueryWrapper<MessageDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).or().eq("parent_id",id);
        List<MessageDO> list = messageMapper.selectList(queryWrapper);
        List<Integer> listId = new ArrayList<>();
        list.forEach(l-> {
            listId.add(l.getId());
        });
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("id",id);
//        columnMap.put("parent_id",id);
        return messageMapper.deleteBatchIds(listId);
    }

    @Override
//    @Cacheable(value = "message",keyGenerator = "springCacheCustomKeyGenerator",cacheManager = "cacheManager1Hour")
    public Map<String, Object> page(int page, int size) {
        Map<String,Object> map = (Map<String,Object>)redisTemplate.opsForValue().get("msg:page");
        if (map != null){
            return map;
        }
        else {
            Page pageInfo = new Page(page,size);
            IPage<MessageDO> iPage = messageMapper.selectPage(pageInfo,null);
            Map<String,Object> pageMap = new HashMap<>(3);
            pageMap.put("total_record",iPage.getTotal());
            pageMap.put("total_page",iPage.getPages());
            pageMap.put("current_data",iPage.getRecords());
            redisTemplate.opsForValue().set("msg:page",pageMap,60,TimeUnit.SECONDS);
            return pageMap;
        }
    }

    /**
     * DO转VO返回前端
     * @param messageDO
     * @return
     */
    private MessageVO invert(MessageDO messageDO){
        assert messageDO!=null;
        MessageVO messageVO = new MessageVO();
        messageVO.setId(messageDO.getId());
        //用户头像
        messageVO.setAvatar("test");
        messageVO.setContent(messageDO.getContent());
        messageVO.setImgs(msgImgService.findByMessageId(messageDO.getId()));
        messageVO.setNickname("test");
        messageVO.setParentId(messageDO.getParentId());
        messageVO.setRole("test");
        messageVO.setGmtCreated(messageDO.getGmtCreated());
        return messageVO;
    }

}
