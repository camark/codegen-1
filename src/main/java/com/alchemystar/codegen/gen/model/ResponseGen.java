/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen.model;

import com.alchemystar.codegen.meta.proto.IntrospectedTable;

/**
 * ResponseGen
 *
 * @Author lizhuyang
 */
public class ResponseGen extends AbstractModelGen {

    public ResponseGen(IntrospectedTable table, String appName) {
        super(table, appName);
    }

    public void genPackage() {
        ret += "package " + "com.alchemystar." + appName + "." + "service.api.auto.response;";
    }

    public void genImports() {
        ret += "\n\n" +
                "import java.util.Date;"
                + "\n\n"
                + "import com.alchemystar." + appName + ".service.api.auto.response.BaseResponse;"
                + "\n\n";
    }

    public void beginClass() {
        ret += "public class " + "Auto" + table.getJavaProperty() + "Response " + "extends BaseResponse {";
        ret += "\n";
    }

    @Override
    public String getFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("../");
        sb.append("app/service/api/src/main/java");
        sb.append(getTargetPath());
        sb.append("service/api/auto/response/");
        sb.append(getFileName(table));
        return sb.toString();
    }

    @Override
    public String getFileName(IntrospectedTable table) {
        return "Auto" + table.getJavaProperty() + "Response.java";
    }
}
