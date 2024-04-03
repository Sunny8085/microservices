package com.bank.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.security.model.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	private com.bank.security.model.UserDetails user;
	
	public CustomUserDetails(com.bank.security.model.UserDetails user) {
		this.user=user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Set<Role> roles=user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>();
		Set<UserRole> userRoles = user.getRoles();
		for(UserRole role : userRoles) {
			String userRole = role.getRoles().getRole_name();
			authorities.add(new SimpleGrantedAuthority(userRole));
		}
		return authorities;
	}	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(user.getStatus().equals("Y"))
			return true;
		else
			return false;
	}

}
