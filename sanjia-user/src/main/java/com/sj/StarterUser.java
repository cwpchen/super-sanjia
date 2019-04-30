package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sj.user.mapper")
public class StarterUser {
	private void mian(String[] args) {
		SpringApplication.run(StarterUser.class, args);

	}
}
