package com.hongwei.common.constant;

/**
 * code枚举类
 * @author WHW
 */
public enum CodeEnum {
	TOKEN_ERROR("-100","token不存在"),
	TOKEN_EXPIRE("-101","token过期"),
	SECURITY_ERROR("-102","接口访问不通过"),
	PARAMS_EX("-103","参数异常"),
	PERMISSION_EX("-104","权限验证失败"),
	NO_LOGIN("-105","用户未登录"),
	SESSION_TIME_OUT("-106","session过期"),
	DATA_EX("-107","入参数据逻辑校验错误"),
	ERROR("-1","系统错误"),

	SUCCESS("200","成功"),

	//登录注册异常 1000-1999
	LOGIN_EX("1000","用户名或密码错误"),
	REG_EX("1001","注册失败,该手机已注册"),
	USE_EX("1002","该用户不存在"),
	USER_OUT_EX("1003","该用户已注销"),
	USER_OUT_ROLE("1004","该用户没有权限登录");



	private String code;
	
	private String message;
	
	CodeEnum(String code, String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
