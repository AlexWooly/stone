package com.stone.demo;

import com.stone.demo.model.MessageDO;
import com.stone.demo.model.OrderDO;
import com.stone.demo.model.Problem;
import com.stone.demo.model.WorkSubmit;
import com.stone.demo.service.MessageService;
import com.stone.demo.service.ProblemService;
import com.stone.demo.service.WorkSubmitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
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
	@Autowired
	MessageService messageService;

	@Test
	void meinset(){
		MessageDO messageDO = new MessageDO();
		messageDO.setContent("test2");
		messageDO.setOpen_id("wlysda");
		messageDO.setTag("TEST2");
		messageDO.setParentId(1);
		messageService.report(messageDO);
	}

	@Test
	void listMes(){
		System.out.println(messageService.list());
	}

	@Test
	void deleMsg(){
		System.out.println(messageService.deleteByBatch(1));
	}

	@Test
	void listMsgVo(){
		System.out.println(messageService.list());
	}

}
