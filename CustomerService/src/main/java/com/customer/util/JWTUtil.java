package com.customer.util;

import java.util.Date;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	private final String SECRETKEY="mysecretkey16";
	private final long EXPIRATION=1000 * 60 * 60;
	
	public String generatetoken(String username, String role) {
		return Jwts.builder()
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, SECRETKEY)
				.compact();
		
	}
	

}
