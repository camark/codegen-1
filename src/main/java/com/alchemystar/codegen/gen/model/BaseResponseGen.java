/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen.model;

import com.alchemystar.codegen.gen.CodeGen;
import com.alchemystar.codegen.meta.proto.IntrospectedTable;

/**
 * BaseResponseGen
 *
 * @Author lizhuyang
 */
public class BaseResponseGen extends CodeGen {

    public BaseResponseGen(IntrospectedTable table, String appName) {
        super(table, appName);
        vmpath = "template/baseResponse.vm";
    }

    @Override
    public String getFileName(IntrospectedTable table) {
        return "BaseResponse.java";
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

}
