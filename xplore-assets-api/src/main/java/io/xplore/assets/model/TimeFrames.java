/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * List of time frames during the week
 */
public class TimeFrames implements Serializable {

    private static final long serialVersionUID = 3492732178122458888L;


    /**
     * Time frames
     */
    public List<TimeFrame> TimeFrames = new ArrayList<>();


    /**
     * Default Constructor
     */
    public TimeFrames() {}

}
