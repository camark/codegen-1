/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen;

import com.alchemystar.codegen.meta.proto.IntrospectedTable;

/**
 * ControllerGen
 *
 * @Author lizhuyang
 */
public class ControllerGen extends CodeGen {

    public ControllerGen(IntrospectedTable table, String appName) {
        super(table, appName);
        vmpath = "template/controller.vm";
    }

    @Override
    public String getFileName(IntrospectedTable table) {
        return "Auto" + table.getJavaProperty() + "Controller.java";
    }

    @Override
    public String getFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("../");
        sb.append("app/service/impl/src/main/java");
        sb.append(getTargetPath());
        sb.append("service/impl/auto/");
        sb.append(getFileName(table));
        return sb.toString();
    }
}
