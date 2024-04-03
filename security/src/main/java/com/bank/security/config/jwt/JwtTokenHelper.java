package com.bank.security.config.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenHelper {
	
	private String SECRET_KEY = "yourBase64EncodedPrivateKeysasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd";
	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; 

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
	
	//validate token only
	boolean validation = false;
	public Boolean validateToken(final String token) {
		try {
			if(!isTokenExpired(token))
				validation = true;
		}catch (ExpiredJwtException e) {
		    System.out.println(" Token expired ");
		}
		return validation; 		
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
		 
	 private Boolean isTokenExpired(String token) {
		 final Date expiration = getExpirationDateFromToken(token);
		 Date curr = new Date();
		 Boolean expi=expiration.before(curr);
		 return expi;
	 }
	
	 
	 public Boolean validateToken(String token,UserDetails userDetails) {
		 final String username = getUsernameFromToken(token);
		 return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	 } 
		 
	//token generation
	public String generateToken(UserDetails userDetails) {
	Map<String,Object> claims=new HashMap<String, Object>();
//		claims.put("roles", userDetails.getAuthorities());
		return doGenerateToken(claims, userDetails.getUsername());
	} 
	
	public String doGenerateToken(Map<String,Object> claims,String user) {
		 return Jwts.builder()
				 .setClaims(claims)
				 .setSubject(user)
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 1000))
				 .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
				 .compact();
	 }
	 //end of Token genetation
}
