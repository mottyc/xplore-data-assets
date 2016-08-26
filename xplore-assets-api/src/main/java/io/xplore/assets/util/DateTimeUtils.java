
/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.util;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {



    /**
     * Get current time stamp in hex value
     * @return
     */
    public static String getHexTimeStamp() {
        long value = Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddhhmmss")));
        return String.format("%X", value);
    }

}
	

