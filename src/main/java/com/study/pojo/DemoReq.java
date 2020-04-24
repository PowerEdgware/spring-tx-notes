package com.study.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoReq {

	@NotBlank
	private String entityName;
	@NotEmpty
	private String remark;
}
