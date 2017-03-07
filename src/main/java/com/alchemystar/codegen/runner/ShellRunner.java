/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.runner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * ShellRunner
 *
 * @Author lizhuyang
 */
public class ShellRunner {

    public static void main(String[] args) {
        Generator generator = new Generator();
        Properties properties = loadFromFile("./config.properties");
        generator.doGenerate(properties);
    }

    public static Properties loadFromFile(String file) {
        if (StringUtils.isBlank(file)) {
            throw new IllegalArgumentException("file parameter can't be blank.");
        }
        Properties p = new Properties();
        FileInputStream fis = null;
        try {
            try {
                fis = new FileInputStream(file);
                p.load(fis);
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
}
