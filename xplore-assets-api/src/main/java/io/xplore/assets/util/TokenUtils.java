
/*
 * Copyright (c) 2016. xplore.io
 *
 */

package io.xplore.assets.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.xplore.assets.messages.TokenData;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * Set of JWT (JSON Web Token) functions
 */
public class TokenUtils {

	private static final String TOKEN_API_SECRET = "XPLOREDATAASSETSSECRETAPIKEYTOKEN";

	/**
	 * Create JSON Web Token
	 * @param accountKey
	 * @param contactKey
	 * @param roles
	 * @param ttlMillis
	 * @return
	 */
	public static String createJWT(int accountKey, int contactKey, String roles, long ttlMillis) {

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(TOKEN_API_SECRET);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(contactKey))
				.setIssuedAt(now)
				.setSubject(roles)
				.setIssuer(String.valueOf(accountKey))
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis > 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	/**
	 * Parse JSON Web Token
	 * @param token Token to parse
	 * @return Token data
	 */
	public static TokenData parseJWT(String token) throws Exception {
		try {
			//This line will throw an exception if it is not a signed JWS (as expected)
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(TOKEN_API_SECRET))
					.parseClaimsJws(token).getBody();

			int account = Integer.parseInt(claims.getIssuer());
			int contact = Integer.parseInt(claims.getId());
			String roles = claims.getSubject();
			Date expire = claims.getExpiration();

			return new TokenData(account, contact, roles, expire);
		} catch (Exception ex) {
			throw new Exception("Invalid token, access denied");
		}
	}

	/**
	 * Parse JSON Web Token and verify user has specified roles
	 * @param token Token to parse
	 * @param roles Roles to check
	 * @return Token data
	 */
	public static TokenData parseJWT(String token, String roles) throws Exception {

		TokenData tokenData = null;

		// First, extract data from token
		try {
			tokenData = parseJWT(token);
		} catch (Exception ex) {
			throw new Exception("Invalid token, access denied");
		}

		// Verify roles
		List<String> tokenRoles = StringUtils.split(tokenData.getRoles());
		List<String> listRoles = StringUtils.split(roles);

		for (String r : tokenRoles) {
			if (listRoles.contains(r)) return tokenData;
		}

		// At this point, no match found
		throw new Exception("Unauthorized action");
	}

}
