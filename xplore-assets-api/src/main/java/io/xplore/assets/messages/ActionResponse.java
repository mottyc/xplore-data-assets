/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

/**
 * Entity action response message
 * This message is returned for any create/update action on entity
 */
public class ActionResponse extends BaseResponse {

    /**
     * The entity key
     */
    private int key = -1;
    public int getKey() { return key; }
    public void setKey(int value) { this.key = value; }

    /**
     * Additional data
     */
    private String data;
    public String getData() { return data; }
    public void setData(String value) { this.data = value; }

    /**
     * Default constructor
     */
    public ActionResponse() {}

    /**
     * Constructor with key
     */
    public ActionResponse(int key) {
        this.setKey(key);
    }

    /**
     * Constructor with key and data
     */
    public ActionResponse(int key, String data) {
        this(key);
        this.setData(data);
    }

    /**
     * Constructor with error message
     * @param message The error message
     * @param args Additional arguments
     */
    public ActionResponse(String message, Object... args) { super(message, args); }

    /**
     * Constructor with exception
     * @param exp The exception
     */
    public ActionResponse(Exception exp) { super(exp); }
}
