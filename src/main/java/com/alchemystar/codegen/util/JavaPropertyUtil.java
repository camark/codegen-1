/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.util;

/**
 * JavaPropertyUtil
 *
 * @Author lizhuyang
 */
public class JavaPropertyUtil {

    public static String getCamelCaseString(String inputString,
                                            boolean firstCharacterUppercase, String prefix) {
        StringBuilder sb = new StringBuilder();

        //this code is to erase the table prefix like "F_"
        if (prefix != null && inputString != null) {

            if (inputString.startsWith(prefix)) {
                inputString = inputString.substring(prefix.length(), inputString.length());
            }
        }

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;

                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }
}
