package com.chenjt.common;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chen jianting on 2019/4/24.
 */
@Component
@Data
public class TaskSet {

	private Set<DeferredResult<ResponseMsg<String>>> set = new HashSet<>();
	private Set<DeferredResult<String>> sset = new HashSet<>();

}