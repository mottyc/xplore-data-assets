
/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Set of String helper functions
 */
public class NumUtils {

	/**
	 * Round double value to two decimal places
	 * @param value
	 * @return
	 */
	public static double round(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}


}
