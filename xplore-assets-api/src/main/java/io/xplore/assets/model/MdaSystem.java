/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;

/**
 * System domain model
 */
public class MdaSystem implements Serializable {

    private static final long serialVersionUID = 3002732178111450000L;

    /**
     * System Key [read-only]
     */
    public int systemKey;

    /**
     * System Name [read-only]
     */
    public String systemName;

    /**
     * Application name [read-only]
     */
    public String appName;

    /**
     * Reference to application expert user
     */
    public String appExpertUsernameKey;

    /**
     * Reference to system owner user
     */
    public String appOwnerUsernameKey;

    /**
     * Reference to module owner user
     */
    public String moduleOwnerUsernameKey;

    /**
     * Display name
     */
    public String displayName;

    /**
     * Description
     */
    public String description;

    /**
     * Department
     */
    public String systemDepartment;

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
    public MdaSystem() {}

}
