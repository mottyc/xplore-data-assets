/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.model.enums;

/**
 * Base enum
 */
public enum _BaseEnum {

    /**
     * Undefined.
     */
    UNDEFINED("");

    protected String value;

     _BaseEnum(){}

    _BaseEnum(String value) { this.value = value;}

    public String getValue() {return value; }

    /**
     * Validate that value is in the enum
     * @param value
     */
    public static void validate(String value) {
        return;
    }

}
