package com.chenjt.service.impl;

import com.chenjt.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by chen jianting on 2019/4/22.
 */
@Service
public class AsyncServiceImpl implements AsyncService {
	@Async("AsyncExecutorPool")
	@Override
	public void sync(String arg) throws InterruptedException {
		Thread.sleep(5000L);
		System.out.println("arg:" + arg);
		System.out.println(Thread.currentThread().getName());
	}
}
