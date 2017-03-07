/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen;

import com.alchemystar.codegen.meta.proto.IntrospectedTable;

/**
 * BizGen
 *
 * @Author lizhuyang
 */
public class BizGen extends CodeGen {

    public BizGen(IntrospectedTable table, String appName) {
        super(table, appName);
        vmpath = "template/biz.vm";
    }

    @Override
    public String getFileName(IntrospectedTable table) {
        return "Auto" + table.getJavaProperty() + "Service.java";
    }

    @Override
    public String getFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("../");
        sb.append("app/biz/src/main/java");
        sb.append(getTargetPath());
        sb.append("biz/auto/");
        sb.append(getFileName(table));
        return sb.toString();
    }

}
