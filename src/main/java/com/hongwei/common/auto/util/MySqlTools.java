package com.hongwei.common.auto.util;

import com.hongwei.common.auto.bean.Column;
import com.hongwei.common.auto.bean.CommonEntity;
import com.hongwei.common.auto.bean.Table;
import com.hongwei.common.auto.constant.MySqlConstant;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 链接数据库
 */
public class MySqlTools {

    private static Connection conn = null;

    private static DatabaseMetaData databaseMetaData = null;

    public static DatabaseMetaData getDatabaseMetaData() throws Exception{
        //链接mysql
        Class.forName(MySqlConstant.DRIVER_NAME);
        Properties props =new Properties();
        props.setProperty("user", MySqlConstant.USERNAME);
        props.setProperty("password", MySqlConstant.PASSWORD);
        props.setProperty("remarks", "true"); //设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
        conn = DriverManager.getConnection(MySqlConstant.URL,props);
        databaseMetaData = conn.getMetaData();
        return databaseMetaData;
    }

    /** 通过表名获取属性列表 */
    public static List<Column> getColumns(String tableName){
        try {
            ResultSet resultSet = MySqlTools.getDatabaseMetaData().getColumns(MySqlConstant.CATALOG, MySqlConstant.SCHEMA,tableName,"%");
            List<Column> columns = new ArrayList<Column>();
            while (resultSet.next()){
                columns.add(new Column(resultSet));
            }
            return columns;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConn();
        }
        return null;
    }

    public static List<Column> getPrimaryKeys(String tableName){
        try {
            ResultSet resultSet = MySqlTools.getDatabaseMetaData().getPrimaryKeys(MySqlConstant.CATALOG, MySqlConstant.SCHEMA, tableName);
            List<Column> pkColumns = new ArrayList<Column>();
            while(resultSet.next()){
                Column c = new Column();
                String name = resultSet.getString("COLUMN_NAME");
                c.setName(CamelCaseUtils.toCamelCase(name));
                c.setDbName(name);
                pkColumns.add(c);
            }
            return pkColumns;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConn();
        }
        return null;
    }

    /** 获取数据库的表集合 */
    public static List<Table> getTables(){
        try {
            ResultSet resultSet = MySqlTools.getDatabaseMetaData().getTables(MySqlConstant.CATALOG, MySqlConstant.SCHEMA, "%", new String[0]);
            List<Table> tables = new ArrayList<Table>();
            while (resultSet.next()){
                tables.add(new Table(resultSet));
            }
            return tables;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConn();
        }
        return null;
    }

    /** 获取数据库的表集合 */
    public static Table getTable(String tableName){
        try {
            ResultSet resultSet = MySqlTools.getDatabaseMetaData().getTables(MySqlConstant.CATALOG, MySqlConstant.SCHEMA, tableName, new String[0]);
            Table table = null;
            while (resultSet.next()){
                table = new Table(resultSet);
            }
            table.setColumns(getColumns(tableName));
            table.setPkColumns(getPrimaryKeys(tableName));
            return table;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConn();
        }
        return null;
    }

    private static void closeConn(){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception{
        String model = "auto";
        //获取当前日期
        SimpleDateFormat sm_date = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sm_year = new SimpleDateFormat("yyyy年");

        Map<String,Object> map = new HashMap<String, Object>();
        //String tableName, String uname, String info, String model, String basePackage
        CommonEntity entity = new CommonEntity("auto_table", "whw", "数据库表", model);
        map.put("entity",entity);
        map.put("classNameLower",entity.getClassNameLower());
        map.put("model",entity.getModel());
        map.put("className",entity.getClassName());
        map.put("package",entity.getBasePackage());
        map.put("date", sm_date.format(new Date()));
        map.put("year", sm_year.format(new Date()));
        FileHelper.createFileByTemp(model,map);
    }
}
