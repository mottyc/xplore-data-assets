/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

/**
 * Base class for all REST Service responses
 *
 */
public abstract class BaseResponse {

    /**
     * Error code (0 for success)
     */
    private int code = 0;
    public int getCode() { return code; }
    public void setCode(int value) { this.code = value; }

    /**
     * Error message
     */
    private String error;
    public String getError() { return error; }
    public void setError(String value) { this.error = value; }

    /**
     * Default constructor
     */
    public BaseResponse() {}

    /**
     * Constructor with status and message
     * @param code The error code (0 for success)
     * @param message The error message
     * @param args Additional arguments
     */
    public BaseResponse(int code, String message, Object... args) {
        this.code = code;
        this.error = String.format(message, args);
    }

    /**
     * Constructor with error message
     * @param message The error message
     * @param args Additional arguments
     */
    public BaseResponse(String message, Object... args) {
        this(-1, message, args);
    }

    /**
     * Constructor with exception
     * @param exp The exception
     */
    public BaseResponse(Exception exp) {
        this(-1, exp.getMessage());
    }

    /**
     * Is this response has error message
     * @return
     */
    public boolean checkIfError() { return (this.code != 0); }

    /**
     * Is this response is valid
     * @return
     */
    public boolean checkIfValid() { return (this.code == 0); }
}
