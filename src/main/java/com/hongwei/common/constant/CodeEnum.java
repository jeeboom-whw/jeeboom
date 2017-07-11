package com.hongwei.common.constant;

/**
 * code枚举类
 * @author WHW
 */
public enum CodeEnum {

	ERROR("-1","系统错误"),
	SUCCESS("200","成功"),

	//参数方面错误
	PARAMS_EX("1000","参数异常"),
	IMPORT_EXCEL_ERROR("1001","导入文件异常或无值"),

	//业务方面错误
	DATA_EX("2000","入参数据逻辑校验错误"),
	LOGIN_ERROR("2001","登陆异常"),
	PERMISSION_EX("2002","权限验证失败"),
	SESSION_TIME_OUT("2003","session过期");



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
