/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;

/**
 * Relation domain model
 */
public class MdaRelation implements Serializable {

    private static final long serialVersionUID = 3040032178111450040L;


    /**
     * Relation Key [read-only]
     */
    public int relationKey;

    /**
     * Relation full name [read-only]
     */
    public String fullRelationName;

    /**
     * Reference to parent column Key [read-only]
     */
    public int columnKeyPar;

    /**
     * Reference to referenced column Key [read-only]
     */
    public int columnKeyRef;

    /**
     * Database name [read-only]
     */
    public String dbName;

    /**
     * Schema name of parent column [read-only]
     */
    public String schemaNamePar;

    /**
     * Table name of parent column [read-only]
     */
    public String tableNamePar;

    /**
     * Column name of parent column [read-only]
     */
    public String columnNamePar;

    /**
     * Schema name of referenced column [read-only]
     */
    public String schemaNameRef;

    /**
     * Table name of referenced column [read-only]
     */
    public String tableNameRef;

    /**
     * Column name of referenced column [read-only]
     */
    public String columnNameRef;

    /**
     * Display name
     */
    public String displayName;

    /**
     * Description
     */
    public String description;

    /**
     * Relation type [read-only]
     */
    public String relationTypeCd;

    /**
     * Relation source [read-only]
     */
    public String relationSource;

    /**
     * Relation score [read-only]
     */
    public double relationScore;

    /**
     * Reference to DXP parent column key [read-only]
     */
    public int dxpColumnGkPar;

    /**
     * Reference to DXP referenced column key [read-only]
     */
    public int dxpColumnGkRef;

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
    public MdaRelation() {}

}
