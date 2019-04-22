package com.chenjt.service;

/**
 * Created by chen jianting on 2019/4/22.
 */
public interface AsyncService {
	void sync(String arg) throws InterruptedException;
}
