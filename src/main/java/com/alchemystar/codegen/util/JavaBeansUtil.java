/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.util;

/**
 * JavaBeansUtil
 *
 * @Author lizhuyang
 */
public class JavaBeansUtil {

    public static String getGetterMethod(String property, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("    "); // for the code style;
        sb.append("public");
        sb.append(" ");
        sb.append(type);
        sb.append(" ");
        sb.append(getGetterMethodName(property, type));
        sb.append("(");
        sb.append(")");
        sb.append(" ");
        sb.append("{");
        sb.append("\n");
        sb.append("        "); // for the code style;
        sb.append("return");
        sb.append(" ");
        sb.append(firstCharLower(property));
        sb.append(";");
        sb.append("\n");
        sb.append("    "); // for the code style
        sb.append("}");
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }

    public static String getSetterMethod(String property, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("    "); // for the code style;
        sb.append("public");
        sb.append(" ");
        sb.append("void");
        sb.append(" ");
        sb.append(getSetterMethodName(property));
        sb.append("(");
        sb.append(type);
        sb.append(" ");
        sb.append(firstCharLower(property));
        sb.append(")");
        sb.append(" ");
        sb.append("{");
        sb.append("\n");
        sb.append("        "); // for the code style;
        sb.append("this." + firstCharLower(property));
        sb.append(" ");
        sb.append("=");
        sb.append(" ");
        sb.append(firstCharLower(property));
        sb.append(";");
        sb.append("\n");
        sb.append("    "); // for the code style
        sb.append("}");
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }

    public static String firstCharLower(String property) {
        return property.substring(0, 1).toLowerCase() + property.substring(1);
    }

    public static String getGetterMethodName(String property, String type) {
        StringBuilder sb = new StringBuilder();

        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }

        if (type.equals("Boolean")) {
            sb.insert(0, "is");
        } else {
            sb.insert(0, "get");
        }

        return sb.toString();
    }

    public static String getSetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();

        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }

        sb.insert(0, "set");

        return sb.toString();
    }

}
