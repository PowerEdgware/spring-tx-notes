package com.study.controller;

import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.study.pojo.RespMsg;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class BeanValidatorFailedController {
	static final Logger log = LoggerFactory.getLogger(BeanValidatorFailedController.class);

	MessageSource messageSource;

	// field validate relation
	@ExceptionHandler(ConstraintViolationException.class)
	public RespMsg<String> handleConstraintViolationException(ConstraintViolationException e) {
		log.error(e.getMessage(), e);
		String error = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining());
		return RespMsg.buildResult(400, error);
	}

	// bean validate relation
	@ExceptionHandler(BindException.class)
	public RespMsg<String> handleBeanBindException(BindException e) {
		log.error(e.getMessage(), e);
		Locale locale=LocaleContextHolder.getLocale();
		String errorMap=e.getBindingResult().getFieldErrors()
				.stream()
				.collect(Collectors.toMap(FieldError::getField, fe->{
					return messageSource.getMessage(fe, locale);
				})).toString();//{"code":401,"message":"{entityName=不能为空, remark=不能为空}"}
				
		return RespMsg.buildResult(401, errorMap);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public RespMsg<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		log.error(e.getMessage(), e);
		return RespMsg.buildResult(500, ImmutableMap.of(e.getParameterName(), e.getMessage()).toString());
	}

	@ExceptionHandler(Exception.class)
	public RespMsg<String> excetion(Exception e) {
		log.error(e.getMessage(), e);
		return RespMsg.buildResult(500, "系统错误:"+e.getMessage());
	}
}
