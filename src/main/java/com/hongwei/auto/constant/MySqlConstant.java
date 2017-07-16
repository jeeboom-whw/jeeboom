package com.hongwei.auto.constant;

import com.hongwei.auto.util.PropertiesUtil;

public class MySqlConstant {

    public final static String DRIVER_NAME = PropertiesUtil.getValue("jdbc.driver");

    public final static String USERNAME = PropertiesUtil.getValue("jdbc.username");

    public final static String PASSWORD = PropertiesUtil.getValue("jdbc.password");

    public final static String URL = PropertiesUtil.getValue("jdbc.url");

    public final static String SCHEMA = "%";

    public final static String CATALOG = null;
}
