package com.chenjt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chen jianting on 2019/4/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg<T> {

	private int code;

	private String msg;

	private T data;

}