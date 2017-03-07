/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.velocity;

/**
 * VelocityUtil
 *
 * @Author lizhuyang
 */

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {

    public static String renderVM(String vmpath, VelocityContext context) {
        // 创建引擎
        VelocityEngine ve = new VelocityEngine();
        // 设置模板加载路径，这里设置的是class下
        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        try {
            // 进行初始化操作
            ve.init();
            // 加载模板，设定模板编码
            Template t = ve.getTemplate(vmpath, "UTF-8");
            // 设置输出
            StringWriter writer = new StringWriter();
            // 将环境数据转化输出
            t.merge(context, writer);

            return writer.toString();

        } catch (Exception e) {
            throw new RuntimeException("模版转化错误!");
        }
    }
}