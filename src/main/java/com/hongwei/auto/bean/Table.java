package com.hongwei.auto.bean;

import com.hongwei.auto.util.CamelCaseUtils;
import com.hongwei.auto.util.PropertiesUtil;

import java.sql.ResultSet;
import java.util.List;

/**
 * ┏━━━━━━━━━━━┓
 * ┃███████████┃
 * ┃███████┏━┓█┃
 * ┃███████┃　┃█┃
 * ┃███████┃从┃█┃
 * ┃███████┃删┃█┃
 * ┃███████┃库┃█┃
 * ┃███████┃到┃█┃
 * ┃███████┃跑┃█┃
 * ┃███████┃路┃█┃
 * ┃███████┃　┃█┃
 * ┃███████┗━┛█┃
 * ┃███████████┃
 * ┗━━━━━━━━━━━┛
 * 表bean
 */
public class Table {

    /** 表名 */
    private String name;
    /** 数据库表名 */
    private String dbName;
    /** 数据库备注 */
    private String label;
    /** 属性对象 */
    private List<Column> columns;
    /** 索引属性对象 */
    private List<Column> pkColumns;

    private final static String PREFIEX = PropertiesUtil.getValue("tableRemovePrefixes");

    public Table(ResultSet resultSet) throws Exception {
        String name = resultSet.getString("TABLE_NAME");
        if( PREFIEX != null && !"".equals(PREFIEX) ){
            name = name.split(PREFIEX)[0];
        }
        this.name = CamelCaseUtils.toCamelCase(name);
        this.dbName = name;
        this.label = resultSet.getString("REMARKS");
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

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getPkColumns() {
        return pkColumns;
    }

    public void setPkColumns(List<Column> pkColumns) {
        this.pkColumns = pkColumns;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
