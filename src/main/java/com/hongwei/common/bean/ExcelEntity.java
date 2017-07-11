package com.hongwei.common.bean;

import com.hongwei.common.interfaces.ImportExcel;
import com.hongwei.common.interfaces.OutputExcel;
import com.hongwei.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * excel基础类
 */
public class ExcelEntity {

    /** 标题 */
    private String title;
    /** 排序 */
    private String sort;
    /** 属性名称 */
    private String fieldName;
    /** 参数类型 */
    private Class classType;

    private static String getStr = "get";

    private static String setStr = "set";

    public ExcelEntity(OutputExcel excel, String fieldName){
        this.fieldName = getStr + StringUtil.upperCase(fieldName);
        this.title = StringUtils.isBlank(excel.title())?"":excel.title();
        this.sort = excel.sort()+"";
    }

    public ExcelEntity(ImportExcel excel, String fieldName, Class classType){
        this.fieldName = setStr + StringUtil.upperCase(fieldName);
        this.classType = classType;
        this.sort = excel.value()+"";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }
}
