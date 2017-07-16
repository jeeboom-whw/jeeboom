package com.hongwei.auto.bean;

import com.hongwei.auto.util.CamelCaseUtils;
import com.hongwei.auto.util.PropertiesUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Column {

    /** 属性名 */
    private String name;
    /** 数据库属性名 */
    private String dbName;
    /** 描述 */
    private String label;
    /** 类型 */
    private String type;
    /** 数据库中的类型 */
    private String dbType;
    /** 长度 */
    private Integer length;
    /** 是否为空 */
    private Boolean nullable;
    private Integer decimalDigits;

    public Column(){

    }

    //转换列
    public Column(ResultSet resultSet) throws SQLException {
        this.label = resultSet.getString("REMARKS");
        String name = resultSet.getString("COLUMN_NAME");
        this.name = name;
        this.dbName = CamelCaseUtils.toCamelCase(name);
        String dbType = resultSet.getString("TYPE_NAME");
        String type = PropertiesUtil.getValue(dbType);
        this.dbType = dbType;
        this.type = type == null?"String":type;
        this.length = resultSet.getInt("COLUMN_SIZE");
        this.decimalDigits = resultSet.getInt("DECIMAL_DIGITS");
        this.nullable = resultSet.getBoolean("NULLABLE");
    }

    //--------------------- get and set --------------------

    public String getName() {
        return name;
    }

    public String getNameUpper() {
        return name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
