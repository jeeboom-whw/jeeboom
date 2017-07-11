package com.hongwei.common.util;

import com.hongwei.common.constant.CodeEnum;
import com.hongwei.common.bean.ResultData;

/**
 * 处理返回值
 * @author WHW
 */
public class ResultUtil {

	/**  成功 */
	public static ResultData<Object> success(Object object){
		ResultData<Object> resultData = new ResultData<Object>();
		resultData.setCode(CodeEnum.SUCCESS.getCode());
		resultData.setMessage(CodeEnum.SUCCESS.getMessage());
		resultData.setData(object);
		return resultData;
	}

	/** 成功 */
	public static ResultData<Object> success(String message,Object object){
		ResultData<Object> resultData = new ResultData<Object>();
		resultData.setCode(CodeEnum.SUCCESS.getCode());
		resultData.setMessage(message);
		resultData.setData(object);
		return resultData;
	}
	
	/** 成功  */
	public static ResultData<Object> success(){
		return success(null);
	}
	
	/** 失败 */
	public static ResultData<Object> error(String code,String message){
		ResultData<Object> resultData = new ResultData<Object>();
		resultData.setCode(code);
		resultData.setMessage(message);
		return resultData;
	}
	
	/** 系统错误 */
	public static ResultData<Object> error(){
		return error(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage());
	}
	
	/** 公共错误 */
	public static ResultData<Object> error(CodeEnum codeEnum){
		return error(codeEnum.getCode(),codeEnum.getMessage());
	}

	/** 权限验证失败 */
	public static ResultData<Object> validateError(String message){
		return error(CodeEnum.PERMISSION_EX.getCode(),message);
	}

	/** 入参基本校验异常 */
	public static ResultData<Object> paramsError(String message){
		return error(CodeEnum.PARAMS_EX.getCode(),message);
	}
	
	/** 入参业务校验异常 */
	public static ResultData<Object> dataError(String message){
		return error(CodeEnum.DATA_EX.getCode(),message);
	}
	
	
}
