/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Schema domain model
 */
public class MdaSchema implements Serializable {

    private static final long serialVersionUID = 3050032178111450050L;

    /**
     * Schema Key [read-only]
     */
    public int schemaKey;

    /**
     * Schema full name [read-only]
     */
    public String fullSchemaName;

    /**
     * Reference to database (domain) Key [read-only]
     */
    public int domainKey;

    /**
     * Database name [read-only]
     */
    public String dbName;

    /**
     * Schema name [read-only]
     */
    public String schemaName;

    /**
     * Display name
     */
    public String displayName;

    /**
     * Description
     */
    public String description;

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
     * List of tables
     */
    public List<MdaTable> tables = new ArrayList<>();

    /**
     * Default Constructor
     */
    public MdaSchema() {}

}
