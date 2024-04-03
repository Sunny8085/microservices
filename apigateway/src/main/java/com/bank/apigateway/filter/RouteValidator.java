package com.bank.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
	
	//pass this end point request
	public static final List<String> openApiEndPoint = List.of(
			"api/v1/reg/checkuser","/api/v1/security/validate","/api/v1/security/authenticate");
	
	public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndPoint
            		.stream()
            		.noneMatch(uri -> request.getURI().getPath().contains(uri));
            

}








