/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen.model;

import com.alchemystar.codegen.meta.proto.IntrospectedTable;

/**
 * ApiModelGen
 *
 * @Author lizhuyang
 */
public class ApiModelGen extends AbstractModelGen {

    public ApiModelGen(IntrospectedTable table, String appName) {
        super(table, appName);
    }

    public void genPackage() {
        ret += "package " + "com.alchemystar." + appName + "." + "service.api.auto.model;";
    }

    public void genImports() {
        ret += "\n\n" +
                "import java.util.Date;"
                + "\n\n";
    }

    public void beginClass() {
        ret += "public class " + "Auto" + table.getJavaProperty() + "Model " + " {";
        ret += "\n";
    }

    @Override
    public String getFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("../");
        sb.append("app/service/api/src/main/java");
        sb.append(getTargetPath());
        sb.append("service/api/auto/model/");
        sb.append(getFileName(table));
        return sb.toString();
    }

    @Override
    public String getFileName(IntrospectedTable table) {
        return "Auto" + table.getJavaProperty() + "Model.java";
    }

}
