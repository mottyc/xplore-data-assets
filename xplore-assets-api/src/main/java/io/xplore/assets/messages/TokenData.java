/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.messages;

import java.util.Date;

/**
 * Token response message
 */
public class TokenData {

    /**
     * Account Key
     */
    private int accountKey;
    public int getAccountKey() { return accountKey; }
    public void setAccountKey(int value) { this.accountKey = value; }

    /**
     * Contact Key
     */
    private int contactKey;
    public int getContactKey() { return contactKey; }
    public void setContactKey(int value) { this.contactKey = value; }

    /**
     * Contact Key
     */
    private String roles;
    public String getRoles() { return roles; }
    public void setRoles(String value) { this.roles = value; }

    /**
     * Token expiration time
     */
    private Date expiresIn;
    public Date getExpiresIn() { return expiresIn; }
    public void setExpiresIn(Date value) { this.expiresIn = value; }

    /**
     * Default constructor
     */
    public TokenData() {}

    /**
     * Constructor with parameters
     * @param accountKey Account Key
     * @param contactKey Contact Key
     */
    public TokenData(int accountKey, int contactKey) { this(accountKey, contactKey, ""); }

    /**
     * Constructor with parameters
     * @param accountKey Account Key
     * @param contactKey Contact Key
     * @param roles Comma separated list of roles
     */
    public TokenData(int accountKey, int contactKey, String roles) { this(accountKey, contactKey, roles, null); }

    /**
     * Constructor with parameters
     * @param accountKey Account Key
     * @param contactKey Contact Key
     * @param roles Comma separated list of roles
     * @param expiration Expiration time (Null for iternity)
     */
    public TokenData(int accountKey, int contactKey, String roles, Date expiration) {
        this.setAccountKey(accountKey);
        this.setContactKey(contactKey);
        this.setRoles(roles);
        this.setExpiresIn(expiration);
    }
}
