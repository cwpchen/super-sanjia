package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sj.user.mapper")
public class StarterUser {
	public static void main(String[] args) {
		//tesssst111111111122
		SpringApplication.run(StarterUser.class, args);
	}
}
