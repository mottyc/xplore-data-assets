/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;

/**
 * Business entity domain model
 */
public class MdaBusinessEntity implements Serializable {

    private static final long serialVersionUID = 3010032178111450010L;

    /**
     * Entity Key [read-only]
     */
    public int businessEntityKey;

    /**
     * Entity Name [read-only]
     */
    public String businessEntityName;

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
     * Default Constructor
     */
    public MdaBusinessEntity() {}

}
