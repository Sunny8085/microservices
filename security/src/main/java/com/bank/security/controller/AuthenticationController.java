package com.bank.security.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.security.config.CustomUserDetails;
import com.bank.security.config.jwt.JwtAuthResponse;
import com.bank.security.config.jwt.JwtTokenHelper;
import com.bank.security.exceptionhandler.UnauthorizedException;

@RestController
@RequestMapping("api/v1/security")
public class AuthenticationController {
	
	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestParam String username,@RequestParam String password ) throws Exception{
		System.out.println("login controller");
		this.authenticate(username, password);
		System.out.println(username+"username"+password+"password");
		final CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
		final String token = jwtTokenHelper.generateToken(userDetails);
		Map<String, Object> mp = new HashMap<>();
		mp.put("role", userDetails.getAuthorities());
		return ResponseEntity.ok(new JwtAuthResponse(token,mp));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(upat);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new UnauthorizedException("INVALID_CREDENTIALS");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/roles")
	@PreAuthorize("hasRole('ROLE_USER','ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
	public ResponseEntity<Set<String>> getRoles(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = authentication.getAuthorities().stream()
		     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		return ResponseEntity.status(HttpStatus.OK).body(roles);
	}
	
	@GetMapping("/validate")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> validateToken(@RequestParam String token){
		if (jwtTokenHelper.validateToken(token) != null) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        } 
	}
	
}
