package com.stone.demo.entity;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 12:37 下午
 * @Version 1.0
 */
public enum OrderStatus {
    /**
     * 未创建
     */
    Creating,
    /**
     * 已支付
     */
    PAYED,
    /**
     * 待支付
     */
    PAYING,
    /**
     * 过期，失效
     */
    FAILED
}
