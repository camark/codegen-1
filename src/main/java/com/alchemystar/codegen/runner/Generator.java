/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alchemystar.codegen.gen.BizGen;
import com.alchemystar.codegen.gen.ControllerGen;
import com.alchemystar.codegen.gen.model.ApiModelGen;
import com.alchemystar.codegen.gen.model.BaseResponseGen;
import com.alchemystar.codegen.gen.model.ResponseGen;
import com.alchemystar.codegen.meta.DatabaseIntrospector;
import com.alchemystar.codegen.meta.proto.IntrospectedTable;
import com.alchemystar.codegen.gen.CodeGen;
import com.alchemystar.codegen.gen.DaoGen;
import com.mysql.jdbc.StringUtils;

/**
 * Generator
 *
 * @Author lizhuyang
 */
public class Generator {

    private Boolean isGenBaseResponse = false;

    public void generate(Properties properties) {
        try {
            doGenerate(properties);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void doGenerate(Properties properties) {
        String jdbcUrl = properties.getProperty("jdbcUrl");
        if (StringUtils.isEmptyOrWhitespaceOnly(jdbcUrl)) {
            throw new RuntimeException("jdbcUrl miss");
        }
        String appName = properties.getProperty("appName");
        if (StringUtils.isEmptyOrWhitespaceOnly(appName)) {
            throw new RuntimeException("appName miss");
        }
        appName = appName.toLowerCase();
        String dbName = properties.getProperty("dbName");
        if (StringUtils.isEmptyOrWhitespaceOnly(dbName)) {
            throw new RuntimeException("dbName miss");
        }
        String dbUserName = properties.getProperty("dbUserName");
        if (StringUtils.isEmptyOrWhitespaceOnly(dbUserName)) {
            throw new RuntimeException("dbUserName miss");
        }
        String password = properties.getProperty("password");
        if (StringUtils.isEmptyOrWhitespaceOnly(password)) {
            throw new RuntimeException("password miss");
        }
        String[] tables = getTables(properties);
        for (String item : tables) {
            DatabaseIntrospector introspector = new DatabaseIntrospector(dbName, item);
            introspector.setJdbcUrl(jdbcUrl);
            introspector.setUser(dbUserName);
            introspector.setPassword(password);
            introspector.init();
            genTableCode(introspector.getIntrospectedTable(), appName);
        }
    }

    private void genTableCode(IntrospectedTable table, String appName) {
        List<CodeGen> list = new ArrayList<CodeGen>();
        list.add(new DaoGen(table, appName));
        list.add(new BizGen(table, appName));
        list.add(new ControllerGen(table, appName));
        list.add(new ResponseGen(table, appName));
        if (!isGenBaseResponse) {
            list.add(new BaseResponseGen(table, appName));
            isGenBaseResponse = true;
        }
        list.add(new ApiModelGen(table, appName));
        for (CodeGen item : list) {
            item.writeToFile();
        }
    }

    private String[] getTables(Properties properties) {
        String tableString = properties.getProperty("tables");
        if (tableString == null) {
            throw new RuntimeException("must specify the tables,and split with ','");
        }
        return tableString.split(",");
    }
}
