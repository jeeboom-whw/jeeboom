package com.hongwei.common.framework.exception;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class PermissionException extends Exception {

    public PermissionException(){
        super("权限验证失败");
    }
}
