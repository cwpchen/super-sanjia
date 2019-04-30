package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sj.store.mapper")
public class storeStarter {

	public static void main(String[] args) {
		SpringApplication.run(storeStarter.class, args);
	}
}
