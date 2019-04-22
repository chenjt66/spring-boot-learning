package com.chenjt.controller;

import com.chenjt.service.JustForTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chen jianting on 2019/4/22.
 */
@RestController
public class AsyncTestController {
	@Autowired
	private JustForTestService justForTestService;

	@RequestMapping(value = "/async", method = RequestMethod.GET)
	public void async(){
		String result = justForTestService.justForTest("chnejt");
		System.out.println(Thread.currentThread().getName() + "=========" + result);
	}
}
