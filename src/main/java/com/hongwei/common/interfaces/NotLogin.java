package com.hongwei.common.interfaces;

import java.lang.annotation.*;

/**
 *  判断用户类or方法是有需要登录认证
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin {
    boolean value() default true;
}
