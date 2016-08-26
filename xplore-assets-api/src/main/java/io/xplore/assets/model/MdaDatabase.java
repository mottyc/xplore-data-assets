/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;

/**
 * Column domain model
 */
public class MdaDatabase implements Serializable {

    private static final long serialVersionUID = 3030032178111450030L;


    /**
     * Database (domain) Key [read-only]
     */
    public int domainKey;

    /**
     * Database full name [read-only]
     */
    public String fullDbName;

    /**
     * Reference to server Key [read-only]
     */
    public int serverKey;

    /**
     * Database name [read-only]
     */
    public String dbName;

    /**
     * Display name
     */
    public String displayName;

    /**
     * Description
     */
    public String description;

    /**
     * Database type [read-only]
     */
    public String dbTypeCd;

    /**
     * Reference to technical owner
     */
    public String technicalOwnerUsernameKey;

    /**
     * Reference to DXP connection id [read-only]
     */
    public int dxpConnectionId;


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
    public MdaDatabase() {}

}
