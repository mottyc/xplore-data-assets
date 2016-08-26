/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;
import java.time.DayOfWeek;

/**
 * Time frame is range of hours in a specific day of week
 */
public class TimeFrame implements Serializable {

    private static final long serialVersionUID = 3492732178122458888L;


    /**
     * Day of week
     */
    public DayOfWeek DayOfWeek;

    /**
     * From time (ISO 8601 format) (e.g. 10:00)
     */
    public String From;

    /**
     * To time (ISO 8601 format) (e.g. 15:30)
     */
    public String To;


    /**
     * Default Constructor
     */
    public TimeFrame() {}

    /**
     * Constructor with parameters
     */
    public TimeFrame(DayOfWeek dow, String from, String to) {
        this.DayOfWeek = dow;
        this.From = from;
        this.To = to;
    }
}
