package com.chenjt.controller;

import com.chenjt.model.POJO.UserInfo;
import com.chenjt.service.JustForTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * Created by chen jianting on 2019/4/22.
 */
@RestController
@Validated
public class AsyncTestController {
	@Autowired
	private JustForTestService justForTestService;

	@RequestMapping(value = "/async", method = RequestMethod.GET)
	public void async(){
		String result = justForTestService.justForTest("chenjt");
		System.out.println(Thread.currentThread().getName() + "=========" + result);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(@Validated @RequestBody UserInfo userInfo,
										 @NotBlank @RequestParam(value = "sign", required = true) String sign){
		return justForTestService.justForTest("111");
	}
}
