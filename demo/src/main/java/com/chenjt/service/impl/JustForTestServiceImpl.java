package com.chenjt.service.impl;

import com.chenjt.service.AsyncService;
import com.chenjt.service.JustForTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by chen jianting on 2019/4/22.
 */
@Service
public class JustForTestServiceImpl implements JustForTestService {
	@Autowired
	private AsyncService asyncService;

	@Override
	public String justForTest(String arg) {
		System.out.println(Thread.currentThread().getName() + "===JustForTestSErviceImpl===" + arg);
		try {
			asyncService.sync(arg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "================666================";
	}
}
