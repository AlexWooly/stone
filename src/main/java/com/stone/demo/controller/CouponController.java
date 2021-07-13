package com.stone.demo.controller;

import com.stone.demo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("add")
    public JsonData getCoupon(@RequestParam(value = "coupon_id",required = true)int couponId){

        String uuid = UUID.randomUUID().toString().replace("-","");

        String lockKey = "lock:coupon:"+ couponId;

        lock(uuid,lockKey);

        return JsonData.buildSuccess();

    }

    public void lock(String uuid,String lockKey){

        Boolean nativeLock = redisTemplate.opsForValue().setIfAbsent(lockKey,uuid,30, TimeUnit.SECONDS);

        //lua
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

        System.out.println(uuid+"加锁状态"+nativeLock);
        if (nativeLock){
            //加锁成功
            try {
                //TODO 做相关业务逻辑
                TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }finally {
                Long result = redisTemplate.execute(new DefaultRedisScript<>(script,Long.class), Arrays.asList(lockKey),uuid);
                System.out.println("解锁状态"+result);
            }
        }else {
            try {
                System.out.println("加锁失败,自旋");
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //睡眠一会再自旋
            lock(uuid,lockKey);
        }

    }
}
