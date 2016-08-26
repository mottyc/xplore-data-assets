package io.xplore.assets.database.converters;

/**
 * Base class for all converters
 */
public abstract class _BaseConverter {

    protected static String convertTime(java.sql.Timestamp sqlTime) {
        try {
            String time = sqlTime.toString();
            return time.substring(0, time.lastIndexOf("."));
        } catch (Exception ex) {
            return "";
        }
    }
}
