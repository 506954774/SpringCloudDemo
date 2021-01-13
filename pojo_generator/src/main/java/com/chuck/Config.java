package com.chuck;

import java.io.Serializable;

/**
 * Builder
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/16  12:56
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    // 数据源相关配置（根据自己的数据库来配置）
    private  String url = "jdbc:mysql://0.0.0.1:3306/test?autoReconnect=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private  String driverName = "com.mysql.cj.jdbc.Driver";
    private  String userName = "";
    private  String userPwd = "";
    private  String daoPackage = "dao";
    private  String[] tableNames;


    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }
}
