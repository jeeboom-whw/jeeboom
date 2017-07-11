package com.hongwei.common.framework.base;

import com.hongwei.common.constant.CodeEnum;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

public class BaseController {
	public final static Logger log = LoggerFactory.getLogger(BaseController.class);
	
	public final ResultData<Object> paramsError(BindingResult result){
		return ResultUtil.paramsError(result.getFieldError().getDefaultMessage());
	}

	/** 成功 */
	public ResultData<Object> success(Object object){
		return ResultUtil.success(object);
	}

	/** 成功 */
	public ResultData<Object> success(String message,Object object){
		return ResultUtil.success(message,object);
	}

	/** 成功 */
	public ResultData<Object> success(){
		return ResultUtil.success(null);
	}

	/** 失败 */
	public ResultData<Object> error(String code,String message){
		return ResultUtil.error(code,message);
	}

	/** 失败 */
	public ResultData<Object> error(){
		return ResultUtil.error(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage());
	}

	/** 失败 */
	public ResultData<Object> error(CodeEnum codeEnum){
		return ResultUtil.error(codeEnum.getCode(),codeEnum.getMessage());
	}

	/** 失败 */
	public ResultData<Object> paramsError(String message){
		return ResultUtil.error(CodeEnum.PARAMS_EX.getCode(),message);
	}
	/** 失败 */
	public ResultData<Object> paramsError(CodeEnum codeEnum){
		return ResultUtil.error(codeEnum.getCode(), codeEnum.getMessage());
	}
	/** 权限验证失败 */
	public ResultData<Object> validateError(String message){
		return ResultUtil.error(CodeEnum.PERMISSION_EX.getCode(),message);
	}

	/** 入参数据失败 */
	public ResultData<Object> dataError(String message){
		return ResultUtil.error(CodeEnum.DATA_EX.getCode(),message);
	}
}
