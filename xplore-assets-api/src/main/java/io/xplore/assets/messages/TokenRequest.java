/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

/**
 * Token request message
 */
public class TokenRequest extends BaseRequest {

    /**
     * Client API key
     */
    private String apiKey;
    public String getApiKey() { return apiKey; }
    public void setApiKey(String value) { this.apiKey = value; }
}
