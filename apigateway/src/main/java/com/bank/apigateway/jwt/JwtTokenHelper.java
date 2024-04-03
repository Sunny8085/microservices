package com.bank.apigateway.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenHelper {
	
	private String SECRET_KEY = "yourBase64EncodedPrivateKeysasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd";
	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	//validation
	public Boolean validateToken(final String token) {
        return  (!isTokenExpired(token));
    }
	
	private Boolean isTokenExpired(String token) {
		 final Date expiration = getExpirationDateFromToken(token);
		 Date curr = new Date();
		 Boolean expi=expiration.before(curr);
		 return expi;
	 }
	
	//fetch token's username
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
		 
	//fetch token's expiration
	public Date getExpirationDateFromToken(String token) {
		Date d=getClaimFromToken(token,Claims::getExpiration);
		return d;
	}
		
	//validation
	public <T>T getClaimFromToken(String token,Function<Claims, T> claimsResolver){
		final Claims claims= getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey()).build()
				.parseClaimsJws(token).getBody();
	}
	
	private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
}
