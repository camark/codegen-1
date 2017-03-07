/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen;

import com.alchemystar.codegen.meta.proto.IntrospectedTable;

/**
 * DaoGen
 *
 * @Author lizhuyang
 */
public class DaoGen extends CodeGen {

    public DaoGen(IntrospectedTable table, String appName) {
        super(table, appName);
        vmpath = "template/dao.vm";
    }

    @Override
    public String getFileName(IntrospectedTable table) {
        return "Auto" + table.getJavaProperty() + "Dao.java";
    }

    @Override
    public String getFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("../");
        sb.append("app/dal/src/main/java");
        sb.append(getTargetPath());
        sb.append("dal/dao/auto/");
        sb.append(getFileName(table));
        return sb.toString();
    }
}
