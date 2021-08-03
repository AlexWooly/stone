package com.stone.demo.wechat.service;

import com.stone.demo.util.JsonData;
import com.stone.demo.wechat.service.dto.AuthUserDto;


import javax.servlet.http.HttpServletRequest;

/**
 * 登录授权服务接口
 *
 * @author zhuhuix
 * @date 2020-04-07
 */
public interface AuthService {

    /**
     * 登录授权
     *
     * @param authUserDto 认证用户请求信息
     * @param request Http请求
     * @return 认证用户返回信息
     */
    JsonData login(AuthUserDto authUserDto, HttpServletRequest request);
}
