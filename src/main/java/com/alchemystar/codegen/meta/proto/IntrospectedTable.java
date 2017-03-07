/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.alchemystar.codegen.meta.proto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alchemystar.codegen.util.JavaPropertyUtil;

/**
 * IntrospectedTable
 *
 * @Author lizhuyang
 */
public class IntrospectedTable {

    protected List<IntrospectedColumn> primaryKeyColumns;
    protected List<IntrospectedColumn> baseColumns;
    protected List<IntrospectedColumn> blobColumns;

    protected String introspectedSchema;
    private String introspectedCatalog;
    private String introspectedTableName;

    public IntrospectedTable() {
        primaryKeyColumns = new ArrayList<IntrospectedColumn>();
        baseColumns = new ArrayList<IntrospectedColumn>();
        blobColumns = new ArrayList<IntrospectedColumn>();
    }

    public IntrospectedTable(String schema, String tableName) {
        primaryKeyColumns = new ArrayList<IntrospectedColumn>();
        baseColumns = new ArrayList<IntrospectedColumn>();
        blobColumns = new ArrayList<IntrospectedColumn>();
        introspectedSchema = schema;
        introspectedTableName = tableName;

    }

    /**
     * 获取对应columnName的结构体
     *
     * @param columnName
     *
     * @return
     */
    public IntrospectedColumn getColumn(String columnName) {
        if (columnName == null) {
            return null;
        } else {
            // search primary key columns
            for (IntrospectedColumn introspectedColumn : primaryKeyColumns) {
                if (introspectedColumn.isColumnNameDelimited()) {
                    if (introspectedColumn.getActualColumnName().equals(columnName)) {
                        return introspectedColumn;
                    }
                } else {
                    if (introspectedColumn.getActualColumnName().equalsIgnoreCase(columnName)) {
                        return introspectedColumn;
                    }
                }
            }

            // search base columns
            for (IntrospectedColumn introspectedColumn : baseColumns) {
                if (introspectedColumn.isColumnNameDelimited()) {
                    if (introspectedColumn.getActualColumnName().equals(columnName)) {
                        return introspectedColumn;
                    }
                } else {
                    if (introspectedColumn.getActualColumnName().equalsIgnoreCase(columnName)) {
                        return introspectedColumn;
                    }
                }
            }

            // search blob columns
            for (IntrospectedColumn introspectedColumn : blobColumns) {
                if (introspectedColumn.isColumnNameDelimited()) {
                    if (introspectedColumn.getActualColumnName().equals(columnName)) {
                        return introspectedColumn;
                    }
                } else {
                    if (introspectedColumn.getActualColumnName().equalsIgnoreCase(columnName)) {
                        return introspectedColumn;
                    }
                }
            }

            return null;
        }
    }

    public void addPrimaryKeyColumn(String columnName) {
        boolean found = false;
        // first search base columns
        Iterator<IntrospectedColumn> iter = baseColumns.iterator();
        while (iter.hasNext()) {
            IntrospectedColumn introspectedColumn = iter.next();
            if (introspectedColumn.getActualColumnName().equals(columnName)) {
                primaryKeyColumns.add(introspectedColumn);
                iter.remove();
                found = true;
                break;
            }
        }

        // search blob columns in the weird event that a blob is the primary key
        if (!found) {
            iter = blobColumns.iterator();
            while (iter.hasNext()) {
                IntrospectedColumn introspectedColumn = iter.next();
                if (introspectedColumn.getActualColumnName().equals(columnName)) {
                    primaryKeyColumns.add(introspectedColumn);
                    iter.remove();
                    found = true;
                    break;
                }
            }
        }
    }

    public void addColumn(IntrospectedColumn introspectedColumn) {
        if (introspectedColumn.isBLOBColumn()) {
            blobColumns.add(introspectedColumn);
        } else {
            baseColumns.add(introspectedColumn);
        }

        introspectedColumn.setIntrospectedTable(this);
    }

    public List<IntrospectedColumn> getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    public boolean hasPrimaryKeyColumns() {
        return primaryKeyColumns.size() > 0;
    }

    public List<IntrospectedColumn> getBaseColumns() {
        return baseColumns;
    }

    public List<IntrospectedColumn> getAllColumns() {
        List<IntrospectedColumn> answer = new ArrayList<IntrospectedColumn>();
        answer.addAll(primaryKeyColumns);
        answer.addAll(baseColumns);
        answer.addAll(blobColumns);

        return answer;
    }

    public String getJavaProperty() {

        return JavaPropertyUtil.getCamelCaseString(this.getIntrospectedTableName(), true, "t_");
    }

    public String getIntrospectedTableName() {
        return introspectedTableName;
    }

    public void setIntrospectedTableName(String introspectedTableName) {
        this.introspectedTableName = introspectedTableName;
    }

    public String getIntrospectedCatalog() {
        return introspectedCatalog;
    }

    public void setIntrospectedCatalog(String introspectedCatalog) {
        this.introspectedCatalog = introspectedCatalog;
    }

    public String getIntrospectedSchema() {
        return introspectedSchema;
    }

    public void setIntrospectedSchema(String introspectedSchema) {
        this.introspectedSchema = introspectedSchema;
    }

    public void setPrimaryKeyColumns(List<IntrospectedColumn> primaryKeyColumns) {
        this.primaryKeyColumns = primaryKeyColumns;
    }

    public void setBaseColumns(List<IntrospectedColumn> baseColumns) {
        this.baseColumns = baseColumns;
    }

    public List<IntrospectedColumn> getBlobColumns() {
        return blobColumns;
    }

    public void setBlobColumns(List<IntrospectedColumn> blobColumns) {
        this.blobColumns = blobColumns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("primaryKeys:").append("\n");
        for (IntrospectedColumn column : primaryKeyColumns) {
            sb.append(column).append("\n");
        }
        sb.append("baseColumns:").append("\n");
        for (IntrospectedColumn column : baseColumns) {
            sb.append(column).append("\n");
        }
        // no need blobColumns
        return sb.toString();
    }
}
