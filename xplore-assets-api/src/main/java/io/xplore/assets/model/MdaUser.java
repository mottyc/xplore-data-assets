/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;

/**
 * User domain model
 */
public class MdaUser implements Serializable {

    private static final long serialVersionUID = 3080032178111450080L;


    /**
     * User Key [read-only]
     */
    public String usernameKey;

    /**
     * First name [read-only]
     */
    public String firstName;

    /**
     * Last name [read-only]
     */
    public String lastName;

    /**
     * Display name
     */
    public String displayName;

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
    public MdaUser() {}

}
