package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTUtil {
	
	private final String SECRETKEY="mysecretkey16";
	
	public Claims validToken(String token) {
		return Jwts.parser()
				.setSigningKey(SECRETKEY)
				.parseClaimsJws(token)
				.getBody();
	}

}
