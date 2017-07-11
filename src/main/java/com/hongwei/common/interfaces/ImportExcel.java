package com.hongwei.common.interfaces;

import java.lang.annotation.*;

/**
 * excel导入注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportExcel {

    //列下标
    int value() default 0;
}
