package com.study.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class RespMsg<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	String message;
	T data;
	
	private RespMsg(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public static <T> RespMsg<T> buildResult(int code,String message,T data) {
		return new RespMsg<T>(code, message);
	}
	
	public static <T> RespMsg<T> buildResult(int code,String message) {
		return new RespMsg<T>(code, message);
	}
}
