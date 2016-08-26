/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity list response message
 * @param <T> Type of object to list
 */
public class EntitiesResponse<T> extends BaseResponse {


    /**
     * List of objects in the current result set
     */
    private List<T> list = new ArrayList<T>();
    public List<T> getList() { return list; }
    public void setList(List<T> value) { this.list = value; }

    /**
     * Default constructor
     */
    public EntitiesResponse() {}

    /**
     * Constructor with error message
     * @param message The error message
     * @param args Additional arguments
     */
    public EntitiesResponse(String message, Object... args) { super(message, args); }

    /**
     * Constructor with exception
     * @param exp The exception
     */
    public EntitiesResponse(Exception exp) { super(exp); }
}
