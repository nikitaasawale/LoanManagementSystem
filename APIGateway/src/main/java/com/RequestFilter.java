package com;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
@Component
public class RequestFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("HTTP request path"+ exchange.getRequest().getURI().getPath());
		String token=exchange.getRequest().getHeaders().getFirst("Authorization");
		if(token==null || token.isEmpty() ){
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
			//throw new InvalidTokenException("Invalid token ");
		}
		System.out.println("Valid token"+token);
		
		
		return chain.filter(exchange);
	}

}
