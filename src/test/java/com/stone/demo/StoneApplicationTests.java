package com.stone.demo;

import com.stone.demo.model.OrderDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class StoneApplicationTests {

	@Autowired
	RedisTemplate<Object,Object> redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("sda",new OrderDO());

	}

}
