package com.chenjt;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 在异步执行的方法中是可能出现异常的，我们可以在任务内部使用 try catch 来处理异常，当任务抛出异常时，Spring 也提供了捕获它的方法。
 * Created by chen jianting on 2019/4/22.
 */
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
		System.out.println("Exception message - " + throwable.getMessage());
		System.out.println("Method name - " + method.getName());
		for (Object param : objects) {
			System.out.println("Parameter value - " + param);
		}

	}
}
