/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen;

import org.apache.velocity.VelocityContext;

import com.alchemystar.codegen.meta.TypeMap;
import com.alchemystar.codegen.meta.proto.IntrospectedTable;
import com.alchemystar.codegen.util.LancerConfigUtil;
import com.alchemystar.codegen.velocity.VelocityUtil;
import com.alchemystar.codegen.util.LancerFileUtil;

/**
 * CodeGen
 *
 * @Author lizhuyang
 */
public abstract class CodeGen {

    protected String vmpath;

    protected String appName;

    protected IntrospectedTable table;

    public CodeGen(IntrospectedTable table, String appName) {
        this.table = table;
        this.appName = appName;
    }

    public String gen() {
        VelocityContext context = new VelocityContext();
        context.put("name", table.getJavaProperty());
        context.put("primaryType", TypeMap.getJavaType(table.getPrimaryKeyColumns().get(0).getJdbcType()));
        context.put("primaryKey", table.getPrimaryKeyColumns().get(0).getJavaProperty());
        context.put("appname", appName);
        fillContext(context);
        return VelocityUtil.renderVM(vmpath, context);
    }

    public void writeToFile() {
        String code = gen();
        LancerFileUtil.writeCode(getFilePath(), code);
    }

    public abstract String getFilePath();

    public abstract String getFileName(IntrospectedTable table);

    public void fillContext(VelocityContext context) {
    }

    public String getTargetPath() {
        return LancerConfigUtil.getPackagePath("com.alchemystar." + appName);
    }
}
