package com.hongwei.common.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * Class工具类
 */
public class ClassUtil {

    public static Class getClass(JoinPoint joinpoint){
        return joinpoint.getSignature().getDeclaringType();
    }

    public static Method getMethod(JoinPoint joinpoint){
        MethodSignature methodSignature = (MethodSignature)joinpoint.getSignature();
        return methodSignature.getMethod();
    }

    //判断是否是异步请求 是返回true 不是返回false
    public static boolean validationAjax(Class c,Method method){
        if(method.isAnnotationPresent(ResponseBody.class)||c.isAnnotationPresent(RestController.class)){
            return true;
        }
        return false;
    }
}
