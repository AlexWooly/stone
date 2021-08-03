package com.stone.demo.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.demo.util.JsonData;
import com.stone.demo.wechat.domain.User;
import com.stone.demo.wechat.repository.UserRepository;
import com.stone.demo.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author NJUPT wly
 * @Date 2021/8/3 7:34 下午
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public JsonData create(User user) {
        return JsonData.buildSuccess(userRepository.insert(user));
    }

    @Override
    public JsonData delete(User user) {
        return JsonData.buildSuccess(userRepository.delete(new QueryWrapper<User>().eq("open_id",user.getOpenId())));
    }

    @Override
    public JsonData update(User user) {
        return JsonData.buildSuccess(userRepository.updateById(user));
    }

    @Override
    public JsonData update(User user, String openId) {
        return JsonData.buildSuccess(userRepository.update(user,new QueryWrapper<User>().eq("open_id",user.getOpenId())));
    }

    @Override
    public JsonData findById(Long id) {
        return JsonData.buildSuccess(userRepository.selectById(id));
    }

    @Override
    public JsonData findByOpenId(String openId) {
        return JsonData.buildSuccess(userRepository.selectOne(new QueryWrapper<User>().eq("open_id",openId)));
    }

    @Override
    public JsonData findByUserName(String userName) {
        return JsonData.buildSuccess(userRepository.selectOne(new QueryWrapper<User>().eq("user_name",userName)));
    }
}
