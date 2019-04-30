package com.sj.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sj.common.config.HttpClientService;

@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter{
	@Autowired
	private HttpClientService client;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(storeInterInit()).addPathPatterns("/store/**");
		
	}
	
	@Bean
	public StoreInterceptor storeInterInit(){
		return new StoreInterceptor(client);
	}

}
