package com.chenjt.model.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by chen jianting on 2019/4/23.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	@NotBlank
	private String name;
	@NotNull
	private Integer age;
	@NotBlank
	private String nickNmae;

	@Override
	public String toString() {
		return "UserInfo{" +
			"name='" + name + '\'' +
			", age=" + age +
			", nickNmae='" + nickNmae + '\'' +
			'}';
	}
}
