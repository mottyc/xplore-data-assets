/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

/**
 * Entity response message
 * This message is returned for any search for unique entity
 *
 * @param <T> The entity type
 */
public class EntityResponse<T> extends BaseResponse {

    /**
     * The entity
     */
    private T entity = null;
    public T getEntity() { return entity; }
    public void setEntity(T value) { this.entity = value; }

    /**
     * Default constructor
     */
    public EntityResponse() {}

    /**
     * Constructor with entity
     */
    public EntityResponse(T entity) {
        this.entity = entity;
    }

    /**
     * Constructor with error message
     * @param message The error message
     * @param args Additional arguments
     */
    public EntityResponse(String message, Object... args) { super(message, args); }

    /**
     * Constructor with exception
     * @param exp The exception
     */
    public EntityResponse(Exception exp) { super(exp); }
}
