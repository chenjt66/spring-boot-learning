package com.chenjt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chen jianting on 2019/4/22.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

	@Bean("AsyncExecutorPool")
	public ExecutorService asyncExecutorPool(){
		return Executors.newCachedThreadPool();
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}
