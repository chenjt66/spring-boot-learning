package com.chenjt;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 上面提到如果 @Async 不指定 Executor 就默认使用 SimpleAsyncTaskExecutor，其实默认的 Executor 是可以使用 AsyncConfigurer 接口来配置的
 * Created by chen jianting on 2019/4/22.
 */
@Configuration
public class SpringAsyncConfig implements AsyncConfigurer {
	@Override
	public Executor getAsyncExecutor() {
		return Executors.newCachedThreadPool();
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new CustomAsyncExceptionHandler();
	}
}
