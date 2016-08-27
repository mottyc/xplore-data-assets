/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Table domain model
 */
public class MdaTable implements Serializable {

    private static final long serialVersionUID = 3070032178111450070L;

    /**
     * Table Key [read-only]
     */
    public int tableKey;

    /**
     * Table full name [read-only]
     */
    public String fullTableName;

    /**
     * Reference to schema key [read-only]
     */
    public int schemaKey;

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
     * Display name
     */
    public String displayName;

    /**
     * Description
     */
    public String description;

    /**
     * Table rows count [read-only]
     */
    public int tableRowCount;

    /**
     * Table columns count [read-only]
     */
    public int tableNumOfCols;

    /**
     * Object type
     */
    public String objectType;

    /**
     * Table primary key [read-only]
     */
    public String tablePk;

    /**
     * Table id [read-only]
     */
    public int tableId;

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
     * List of columns
     */
    public List<MdaColumn> columns = new ArrayList<>();

    /**
     * Default Constructor
     */
    public MdaTable() {}

}
