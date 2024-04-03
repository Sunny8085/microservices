package com.bank.apigateway.filter;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import com.bank.apigateway.jwt.JwtTokenHelper;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	@Autowired
	private RouteValidator validator;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	public GatewayFilter apply(Config config) {
		try {
	    return (exchange, chain) -> processRequest(exchange, chain);
		}catch(Exception e) {
			e.printStackTrace();
            return null;
		}
	}
	private Mono<Void> processRequest(ServerWebExchange exchange, GatewayFilterChain chain) {
			ServerHttpRequest request = null;
			if(validator.isSecured.test(exchange.getRequest())) {
				//check header contain token or not
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing authorization header");
				}
				try {
					String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
					if(authHeader != null && authHeader.startsWith("Bearer ")) {
						
						String uri ="http://localhost:8086/api/v1/security/roles";
						HttpHeaders header = new HttpHeaders();
						header.set("Authorization", authHeader);
						RequestEntity<Void> requestEntity = RequestEntity.get(uri)
				                .headers(header).build();
						ResponseEntity<Set<String>> tokenRole = restTemplate.exchange(requestEntity,new ParameterizedTypeReference<Set<String>>(){});
						
						if(tokenRole != null && tokenRole.getStatusCode() == HttpStatus.OK) {
							List<String> allowedUrls = Arrays.asList("/admin", "/api/v1/reg/login", "/public", "/special", "/another");
							  
							Set<String> userRoles = tokenRole.getBody();
							exchange.getRequest().mutate().header("Role", "ROLE_USER");
							if (userRoles != null && userRoles.stream().anyMatch(role -> role.contains("ROLE_USER"))) {
								if(allowedUrls.stream().anyMatch(url -> exchange.getRequest().getPath().toString().startsWith(url))) {
									return chain.filter(exchange.mutate().request(request).build());
								}else {
                                    return Mono.error(new RuntimeException("Access to this resource is not allowed"));
                                }
			                } else {
			                    throw new RuntimeException("Insufficient privileges");
			                }
						}
						//or
	//					jwtTokenHelper.validateToken(authHeader);
					}
				}catch(Exception e) {
					System.out.println("invalid access......");
					throw new RuntimeException("unauthorized access to application");
				}
			}
			return chain.filter(exchange.mutate().request(request).build());
	}
	
	public static class Config {
    }
	
	public AuthenticationFilter() {
        super(Config.class);
    }
}
