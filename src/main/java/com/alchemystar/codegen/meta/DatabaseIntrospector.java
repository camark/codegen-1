/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.meta;

/**
 * DatabaseIntrospector
 *
 * @Author lizhuyang
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.alchemystar.codegen.meta.proto.IntrospectedColumn;
import com.alchemystar.codegen.meta.proto.IntrospectedTable;

/**
 * 用来捞取与存储db里面的schema,并转换为java结构
 * Created by lizhuyang on 2015/1/9.
 */
public class DatabaseIntrospector {

    IntrospectedTable introspectedTable;
    //Connection Properties
    String jdbcUrl;
    String user;
    String password;

    DatabaseMetaData metaData;

    public DatabaseIntrospector(String dbName, String tableName) {
        introspectedTable = new IntrospectedTable(dbName, tableName);
    }

    public void init() {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("getConnection Error,jdbcUrl=" + jdbcUrl + ",user=" + user + ",password=" + password);
        }
        try {
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getMetaData error");
            System.exit(1);
        }
        //顺序必须如此
        caculateColumns(metaData);
        caculatePrimaryKey(metaData);

    }

    private void caculateColumns(DatabaseMetaData metaData) {

        ResultSet rs = null;
        try {
            rs = metaData.getColumns("", introspectedTable.getIntrospectedSchema(),
                    introspectedTable.getIntrospectedTableName(), null);

            while (rs.next()) {
                IntrospectedColumn introspectedColumn = new IntrospectedColumn();
                introspectedColumn.setJdbcType(rs.getInt("DATA_TYPE")); //$NON-NLS-1$
                introspectedColumn.setLength(rs.getInt("COLUMN_SIZE")); //$NON-NLS-1$
                introspectedColumn.setActualColumnName(rs.getString("COLUMN_NAME")); //$NON-NLS-1$
                introspectedColumn.setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable); //$NON-NLS-1$
                introspectedColumn.setScale(rs.getInt("DECIMAL_DIGITS")); //$NON-NLS-1$
                introspectedColumn.setRemarks(rs.getString("REMARKS")); //$NON-NLS-1$
                introspectedColumn.setDefaultValue(rs.getString("COLUMN_DEF")); //$NON-NLS-1$

                if (rs.getString("IS_AUTOINCREMENT").equals("Yes")) {

                    introspectedColumn.setAutoIncrement(true);
                } else {
                    introspectedColumn.setAutoIncrement(false);
                }

                introspectedTable.addColumn(introspectedColumn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void caculatePrimaryKey(DatabaseMetaData metaData) {
        ResultSet rs = null;
        String cataLog;

        try {
            rs = metaData.getPrimaryKeys("", introspectedTable.getIntrospectedSchema(),
                    introspectedTable.getIntrospectedTableName());
            Map<Short, String> keyColumns = new TreeMap<Short, String>();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                short keySeq = rs.getShort("KEY_SEQ");
                keyColumns.put(keySeq, columnName);
            }
            for (String columnName : keyColumns.values()) {
                introspectedTable.addPrimaryKeyColumn(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            closeResultSet(rs);
        }
    }

    public Connection getConnection() {

        try {
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) clazz.newInstance();
            return driver.connect(jdbcUrl, props);
        } catch (Exception e) {
            System.out.println("getConnection Error,jdbcUrl=" + jdbcUrl + ",user=" + user + ",password=" + password);
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // ignore
                ;
            }
        }
    }

    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DatabaseMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(DatabaseMetaData metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbcUrl:").append(jdbcUrl).append("\n");
        sb.append("user:").append(user).append("\n");
        sb.append("password:").append(password).append("\n");
        sb.append("Table.").append(introspectedTable).append("\n");
        return sb.toString();
    }
}