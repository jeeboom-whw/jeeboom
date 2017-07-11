package com.hongwei.common.bean;


import com.hongwei.common.util.DateUtils;

import java.io.Serializable;

public class ResultData<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private String code;
	
	private String message;
	
	private T data;
	
	private String sysTime;
	
	public ResultData() {
		super();
		this.sysTime = DateUtils.format();
	}

	public ResultData(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.sysTime = DateUtils.format();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getSysTime() {
		return sysTime;
	}

	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
 }
