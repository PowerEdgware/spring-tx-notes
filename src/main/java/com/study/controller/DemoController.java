package com.study.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.pojo.DemoReq;
import com.study.service.DemoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated // 校验参数
public class DemoController {

	static Logger log = LoggerFactory.getLogger(DemoController.class);

	@NotNull
	final DemoService demoService;

	@GetMapping("/testSave")
	public String saveEntity(@Valid /* 校验Bean */DemoReq demoReq) {//org.springframework.validation.BindException
		demoService.save(demoReq);
		return "OK";
	}

	@GetMapping("/save")
	public Object save(@NotBlank @Size(min = 2, max = 50) @RequestParam(name="name",required=false) String name,
			@NotEmpty @RequestParam("remark") String remark) {//javax.validation.ConstraintViolationException
		//org.springframework.web.bind.MissingServletRequestParameterException
		return demoService.save(name, remark);
	}
	
	//MethodArgumentNotValidException
}
