package com.stone.demo.redis;

public class RateLimiterException extends Throwable {
    public RateLimiterException(String msg){
        super("api限流了"+msg);
    }
}
