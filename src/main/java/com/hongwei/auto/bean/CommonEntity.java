package com.hongwei.auto.bean;

import com.hongwei.auto.util.MySqlTools;
import com.hongwei.auto.util.PropertiesUtil;
import com.hongwei.common.util.GlobalValue;

public class CommonEntity {

    private String className;

    private String classNameLower;

    private String  uname;

    private String info;

    private String model;

    private String basePackage;

    private Table table;

    public CommonEntity(String tableName, String uname, String info, String model){
        this.table =  MySqlTools.getTable(tableName);
        this.className = this.table.getNameUpper();
        this.classNameLower = this.table.getName();
        this.uname = uname;
        this.info = info;
        this.model = model;
        this.basePackage = GlobalValue.basepackage + "." + model;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNameLower() {
        return classNameLower;
    }

    public void setClassNameLower(String classNameLower) {
        this.classNameLower = classNameLower;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
