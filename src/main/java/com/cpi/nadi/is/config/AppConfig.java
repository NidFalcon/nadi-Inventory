package com.cpi.nadi.is.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cpi.nadi.is.service.impl.UserServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public UserServiceImpl userService() {
		return new UserServiceImpl();
	}
}
