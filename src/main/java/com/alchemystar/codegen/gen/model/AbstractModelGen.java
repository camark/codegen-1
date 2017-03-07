/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.gen.model;

import java.util.List;

import com.alchemystar.codegen.meta.proto.IntrospectedColumn;
import com.alchemystar.codegen.gen.CodeGen;
import com.alchemystar.codegen.meta.TypeMap;
import com.alchemystar.codegen.meta.proto.IntrospectedTable;
import com.alchemystar.codegen.util.JavaBeansUtil;

/**
 * AbstractModelGen
 *
 * @Author lizhuyang
 */
public abstract class AbstractModelGen extends CodeGen {

    protected String ret = "";

    public AbstractModelGen(IntrospectedTable table, String appName) {
        super(table, appName);
    }

    public String gen() {
        genPackage();
        genImports();
        beginClass();
        genMembers();
        genMethods();
        closeClass();
        return ret;
    }

    public abstract void genPackage();

    public void genImports() {
        ret += "\n\n" +
                "import java.util.Date;"
                + "\n\n"
                + "import com.alchemystar.superhero.service.rest.model.BaseResponse;"
                + "\n\n";
    }

    public abstract void beginClass();

    public void genMembers() {
        List<IntrospectedColumn> list = table.getAllColumns();
        for (IntrospectedColumn column : list) {
            ret += "    "; // for the code style
            ret += "private";
            ret += " ";
            ret += TypeMap.getJavaType(column.getJdbcType());
            ret += " ";
            ret += column.getJavaProperty();
            ret += ";";
            ret += "\n\n"; // for the code stype
        }
    }

    public void genMethods() {
        List<IntrospectedColumn> list = table.getAllColumns();
        for (IntrospectedColumn column : list) {
            ret += JavaBeansUtil.getGetterMethod(column.getJavaProperty(), TypeMap.getJavaType(column.getJdbcType()));
            ret += JavaBeansUtil.getSetterMethod(column.getJavaProperty(), TypeMap.getJavaType(column.getJdbcType()));

        }
    }

    public void closeClass() {
        ret += "}";
    }
}
