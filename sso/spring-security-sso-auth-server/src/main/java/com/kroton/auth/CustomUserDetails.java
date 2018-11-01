package com.kroton.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User Details - Represents information about a logged in user.
 */
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1663237312692707778L;

	/**
	 * The entity that represents the currently logged in user.
	 */
	private final Usuario usuarioModel;

	/**
	 * List of Roles associated w/ the logged in user.
	 */
	private final List<GrantedAuthority> authorities;

	/**
	 * Constructor.
	 */
	public CustomUserDetails(final Usuario usuarioModel) {
		// set model
		this.usuarioModel = usuarioModel;

		// Generate authorities/roles
		List<GrantedAuthority> roles = new ArrayList<>();

		// Everyone gets user
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		// Save to immutable collection.
		authorities = Collections.unmodifiableList(roles);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return usuarioModel.getConta();
	}

	@Override
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
