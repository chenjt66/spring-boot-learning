package com.chenjt.model.POJO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by chen jianting on 2019/4/23.
 */
@Data
public class UserInfo {
	@NotBlank
	private String name;
	@NotNull
	private Integer age;
	@NotBlank
	private String nickNmae;
}
