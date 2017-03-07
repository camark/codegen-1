/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.meta;

import java.util.HashMap;
import java.util.Map;

import com.alchemystar.codegen.meta.proto.ColumnType;

/**
 * TypeMap
 *
 * @Author lizhuyang
 */
public class TypeMap {

    public static Map<Integer, String> map;

    static {
        map = new HashMap<Integer, String>();
        map.put(ColumnType.DECIMAL.getCode(), "Double");
        map.put(ColumnType.BIGINT.getCode(), "Long");
        map.put(ColumnType.TINY.getCode(), "Integer");
        map.put(ColumnType.SHORT.getCode(), "Integer");
        map.put(ColumnType.LONG.getCode(), "Long");
        map.put(ColumnType.FLOAT.getCode(), "Integer"); // for the orm framework
        map.put(ColumnType.SHORT.getCode(), "Short");
        map.put(ColumnType.NULL.getCode(), "null");
        map.put(ColumnType.TIMESTAMP.getCode(), "Date");
        map.put(ColumnType.LONGLONG.getCode(), "Long");
        map.put(ColumnType.INT24.getCode(), "Integer");
        map.put(ColumnType.DATE.getCode(), "Date");
        map.put(ColumnType.TIME.getCode(), "Date");
        map.put(ColumnType.VAR_STRING_V2.getCode(), "String");
        map.put(ColumnType.YEAR.getCode(), "Date");
        map.put(ColumnType.NEWDATE.getCode(), "Date");
        map.put(ColumnType.VARCHAR.getCode(), "String");
        map.put(ColumnType.BIT.getCode(), "Byte");
        map.put(ColumnType.TIMESTAMP_V2.getCode(), "Date");
        map.put(ColumnType.DATETIME_V2.getCode(), "Date");
        map.put(ColumnType.TIME_V2.getCode(), "Date");
        map.put(ColumnType.NEWDECIMAL.getCode(), "Double");
        map.put(ColumnType.ENUM.getCode(), "Enum");
        map.put(ColumnType.SET.getCode(), "Set");
        map.put(ColumnType.TINY_BLOB.getCode(), "Blob");
        map.put(ColumnType.MEDIUM_BLOB.getCode(), "Blob");
        map.put(ColumnType.LONG_BLOB.getCode(), "Blob");
        map.put(ColumnType.BLOB.getCode(), "Blob");
        map.put(ColumnType.VAR_STRING.getCode(), "String");
        map.put(ColumnType.STRING.getCode(), "String");
        map.put(ColumnType.GEOMETRY.getCode(), "Geometry");
        map.put(ColumnType.DATE_ALIAS.getCode(), "Date");
        map.put(ColumnType.BYTE.getCode(),"Byte");
    }

    public static String getJavaType(int jdbcType) {
        String javaType = map.get(jdbcType);
        if (javaType == null) {
            return "Unknown";
        } else {
            return javaType;
        }
    }

}
