
/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.rest;


import io.xplore.assets.messages.TokenData;
import io.xplore.assets.util.TokenUtils;

import javax.servlet.http.HttpServletRequest;



/**
 * Base class for all REST API with common utility functions
 * @author mottyc
 *
 */
public abstract class BaseResource {

	/**
	 * Get the caller IP address
	 * @param request
	 * @return
	 */
	protected String getCallerIpAddress(HttpServletRequest request) {
		return request.getRemoteAddr();
	}


	// --------------- Token helpers -----------------------------------------------------------------------------------
	/**
	 * Parse JSON Web Token
	 * @param token Token to parse
	 * @return Token data
	 */
	protected TokenData parseJWT(String token) throws Exception {

		return new TokenData(0, 0, "User");
		// return TokenUtils.parseJWT(token);
	}

	/**
	 * Parse JSON Web Token and verify user has specified roles
	 * @param token Token to parse
	 * @param roles Roles to check
	 * @return Token data
	 */
	protected TokenData parseJWT(String token, String roles) throws Exception {

		return new TokenData(0, 0, "Admin");
		//return TokenUtils.parseJWT(token, roles);
	}

}
