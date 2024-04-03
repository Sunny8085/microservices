package com.bank.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bank.security.config.jwt.JwtAuthenticationEntryPoint;
import com.bank.security.config.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	private static final String[] AUTH_WHITELIST = {"/actuator/**"};
	
	@Bean(name="authManager")
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
//	@Bean
//    public JwtAuthenticationFilter authenticationJwtTokenFilter() {
//        return new JwtAuthenticationFilter();
//    }
	
//	@Autowired
//	private final JwtAuthenticationFilter jwtAuthFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Bean(name="mySecurityFilterChain")
	protected SecurityFilterChain ecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/v1/security/validate/**").hasAnyAuthority("ROLE_USER")
				.requestMatchers("/api/v1/security/authenticate/**").permitAll()
				.anyRequest().authenticated())
		.exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(daoAuthenticationProvider());
		return http.build();
	}
	
}
