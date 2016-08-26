/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;

/**
 * Column domain model
 */
public class MdaColumn implements Serializable {

    private static final long serialVersionUID = 3020032178111450020L;

    /**
     * Column Key [read-only]
     */
    public int columnKey;

    /**
     * Column full name [read-only]
     */
    public String fullColumnName;

    /**
     * Reference to table Key [read-only]
     */
    public int tableKey;

    /**
     * Database name [read-only]
     */
    public String dbName;

    /**
     * Schema name [read-only]
     */
    public String schemaName;

    /**
     * Table name [read-only]
     */
    public String tableName;

    /**
     * Column name [read-only]
     */
    public String columnName;

    /**
     * Display name
     */
    public String displayName;

    /**
     * Description
     */
    public String description;

    /**
     * Data type [read-only]
     */
    public String columnDataType;

    /**
     * Is primary key [read-only]
     */
    public boolean isPk;

    /**
     * Primary key source [read-only]
     */
    public String columnPkSource;

    /**
     * Column id [read-only]
     */
    public int columnId;

    /**
     * Table id [read-only]
     */
    public int tableId;

    /**
     * Reference to DXP column key [read-only]
     */
    public int dxpColumnGk;

    /**
     * Reference to DXP table key [read-only]
     */
    public int dxpTableGk;

    /**
     * Create date in <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> format (e.g. YYYY-MM-DDThh:mm:ss+00:00) [read-only]
     */
    public String createDate;

    /**
     * Update date in <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> format (e.g. YYYY-MM-DDThh:mm:ss+00:00) [read-only]
     */
    public String updateDate;

    /**
     * Who updated this object [read-only]
     */
    public String updateBy;


    /**
     * Default Constructor
     */
    public MdaColumn() {}

}
