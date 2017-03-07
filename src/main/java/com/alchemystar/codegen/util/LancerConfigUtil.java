/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.util;

/**
 * LancerConfigUtil
 *
 * @Author lizhuyang
 */
public class LancerConfigUtil {

    public static String getPackagePath(String targetPackage) {
        String[] spilts = targetPackage.split("\\.");
        return getSpilts(spilts);
    }

    private static String getSpilts(String[] spilts) {
        String path = "";
        for (String item : spilts) {
            path += "/" + item;
        }
        path += "/";
        return path;
    }
}
