package com.stone.demo;

import com.stone.demo.model.OrderDO;
import com.stone.demo.model.Problem;
import com.stone.demo.model.WorkSubmit;
import com.stone.demo.service.ProblemService;
import com.stone.demo.service.WorkSubmitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class StoneApplicationTests {

	@Autowired
	RedisTemplate<Object,Object> redisTemplate;

	@Autowired
	WorkSubmitService workSubmitService;

	@Autowired
	ProblemService problemService;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("sda",new OrderDO());

	}
	@Test
	void test() {
		WorkSubmit workSubmit = new WorkSubmit();
		workSubmit.setDetail("tsst");
		workSubmit.setName("word");
		workSubmit.setPath("www.");
		workSubmit.setOpen_id("123");
		workSubmit.setPhone(1223);
		workSubmitService.submit(workSubmit);
	}
	@Test
	void pro() {
		Problem problem = new Problem();
		problem.setDetail("test1");
		problem.setType("test");
		problem.setOpenId("wly");
		problemService.problemSubmit(problem);
	}

}
