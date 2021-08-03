package com.stone.demo.wechat.service;

import com.stone.demo.util.JsonData;
import com.stone.demo.wechat.domain.User;

/**
 * @Author NJUPT wly
 * @Date 2021/8/3 7:33 下午
 * @Version 1.0
 */
public interface UserService {
    /**
     * 增加用户
     *
     * @param user 待新增的用户
     * @return 增加成功的用户
     */
    JsonData create(User user);

    /**
     * 删除用户
     *
     * @param user 待删除的用户
     */
    JsonData delete(User user);

    /**
     * 修改用户
     *
     * @param user 待修改的用户
     * @return 修改成功的用户
     */
    JsonData update(User user);

    /**
     * 修改用户--支持微信端
     *
     * @param user 待修改的用户
     * @param openId 微信openId
     * @return 修改成功的用户
     */
    JsonData update(User user,String openId);

    /**
     * 根据id查找用户
     *
     * @param id 用户id
     * @return id对应的用户
     */
    JsonData findById(Long id);

    /**
     * 用于微信注册用户查找：根据openId查找用户
     *
     * @param openId 微信openId
     * @return openId对应的用户
     */
    JsonData findByOpenId(String openId);

    /**
     * 用于微信注册用户查找：根据userName查找用户
     *
     * @param userName 用户帐号
     * @return 用户帐号对应的用户
     */
    JsonData findByUserName(String userName);
}
