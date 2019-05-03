package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sj.search.mapper")
public class StarterSearch {
	public static void main(String[] args) {
		SpringApplication.run(StarterSearch.class, args);
	}
}
