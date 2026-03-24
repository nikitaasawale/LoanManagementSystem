package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class JWTFilter implements GlobalFilter{
	@Autowired
	public JWTUtil jwtutil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String path=exchange.getRequest().getURI().getPath();
		if(path.contains("/auth/login") || path.contains("/api/customer/register")) {
			return chain.filter(exchange);
		}
		String authheader=exchange.getRequest().getHeaders().getFirst("Authorization");
		if(authheader==null || !authheader.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		String token=authheader.substring(7);
		try {
			jwtutil.validToken(token);
		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}
	

}
