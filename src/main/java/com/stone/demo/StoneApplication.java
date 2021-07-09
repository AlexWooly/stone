package com.stone.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.stone.demo.mapper")
public class StoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoneApplication.class, args);
	}

}
