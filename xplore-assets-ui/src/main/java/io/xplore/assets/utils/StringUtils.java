
/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

/**
 * Set of String helper functions
 */
public class StringUtils {

	/**
	 * Is this string empty
	 * @param value The string to test
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return (value == null) ? true : value.isEmpty();
	}

	/**
	 * Is this string not empty
	 * @param value The string to test
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		return ((value != null) && (!value.isEmpty()));
	}

	/**
	 * Convert list of items to tokenized string
	 * @param items The list of items to join
	 * @param sep The separator
	 * @return Tokenized string
	 */
	public static String join(Collection<?> items, String sep) {

		String list = "";

		if (items != null) {
			for (Object it : items) {
				list = (list.length() == 0) ? it.toString() : String.format("%s%s%s", list, sep, it.toString());
			}
		}
		return list;
	}

	/**
	 * Convert list of items to comma-separated list as string
	 * @param items The list of items to join
	 * @return Comma separated string
	 */
	public static String join(Collection<?> items) { return join(items, ","); }

	/**
	 * Split tokenized string to list of strings
	 * @param list Tokenized list string
	 * @param token The seperator
	 * @return List of string
	 */
	public static List<String> split(String list, String token) {
		return (isEmpty(list)) ? null : Arrays.asList(list.split(token));
	}

	/**
	 * Split comma-separated string to list of strings
	 * @param list comma-separated list string
	 * @return List of string
	 */
	public static List<String> split(String list) { return split(list, ","); }

	/**
	 * Convert comma separated list to enum set
	 * @param eClass enum class
	 * @param str string
	 * @return
	 */
	public static <E extends Enum<E>> EnumSet<E> valueOf(Class<E> eClass, String str) {
		String[] arr = str.substring(1, str.length() - 1).split(",");
		EnumSet<E> set = EnumSet.noneOf(eClass);
		for (String e : arr) set.add(E.valueOf(eClass, e.trim()));
		return set;
	}


	/**
	 * Convert date from specific display format to another
	 *
	 * @param input  input display format in ISO 8601 (e.g. YYYY-MM-DDThh:mm:ss+00:00)
	 * @param format
	 * @return
	 */
	public static String convertDate(String input, String format) {

		try {
			OffsetDateTime dt = OffsetDateTime.parse(input);
			return dt.format(DateTimeFormatter.ofPattern(format));
		} catch (Exception ex) {
			return input;
		}
	}

	/**
	 * Get the current UTC time in ISO 8601 (e.g. YYYY-MM-DDThh:mm:ss+00:00)
	 * @return
	 */
	public static String getCurrentTimeUTC() {
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		return utc.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
}
