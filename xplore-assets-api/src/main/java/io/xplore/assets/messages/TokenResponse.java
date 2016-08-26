/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

/**
 * Token response message
 */
public class TokenResponse extends BaseResponse {

    /**
     * Access token to use
     */
    private String accessToken;
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String value) { this.accessToken = value; }

    /**
     * Token expiration time (seconds)
     */
    private int expiresIn;
    public int getExpiresIn() { return expiresIn; }
    public void setExpiresIn(int value) { this.expiresIn = value; }

    /**
     * Default constructor
     */
    public TokenResponse() {}

    /**
     * Constructor with error message
     * @param expiresIn Token expiration time (seconds)
     * @param token Access token
     */
    public TokenResponse(int expiresIn, String token) {
        this.setExpiresIn(expiresIn);
        this.setAccessToken(token);
    }

    /**
     * Constructor with error message
     * @param message The error message
     * @param args Additional arguments
     */
    public TokenResponse(String message, Object... args) { super(message, args); }

    /**
     * Constructor with exception
     * @param exp The exception
     */
    public TokenResponse(Exception exp) { super(exp); }
}
