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
public class Statistics implements Serializable {

    private static final long serialVersionUID = 3070032156111450095L;

    /**
     * Number of entities [read-only]
     */
    public long entitiesCount;

    /**
     * Number of systems [read-only]
     */
    public long systemsCount;

    /**
     * Number of servers [read-only]
     */
    public long serversCount;

    /**
     * Number of databases [read-only]
     */
    public long databasesCount;

    /**
     * Number of schemas [read-only]
     */
    public long schemasCount;

    /**
     * Number of tables [read-only]
     */
    public long tablesCount;

    /**
     * Number of columns [read-only]
     */
    public long columnsCount;

    /**
     * Number of relations [read-only]
     */
    public long relationsCount;

    /**
     * Number of users [read-only]
     */
    public long usersCount;


    /**
     * Default Constructor
     */
    public Statistics() {}

}
