package com.hongwei.common.interfaces;

import java.lang.annotation.*;

/**
 * excel导出注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OutputExcel {

    //列排序
    int sort() default 0;
    //列标题
    String title();
}
