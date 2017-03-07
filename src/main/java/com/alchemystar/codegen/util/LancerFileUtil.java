/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * LancerFileUtil
 *
 * @Author lizhuyang
 */
public class LancerFileUtil {

    public static void writeCode(String path, String code) {
        if (!createFile(path)) {
            return;
        }
        File file = new File(path);
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            writer.write(code);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            System.out.println("create file " + destFileName + " fail，file already exists！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("create file " + destFileName + " fail，must not be dir！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            if (!file.getParentFile().mkdirs()) {
                System.out.println("create the target file fail！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("create file " + destFileName + " success！");
                return true;
            } else {
                System.out.println("create file " + destFileName + " fail！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("create file " + destFileName + " fail！" + e.getMessage());
            return false;
        }
    }
}
