package com.kroton.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String conta) throws UsernameNotFoundException {
		Usuario usuario = repo.findByConta(conta);
		if (usuario == null) {
			throw new UsernameNotFoundException(conta);
		}
		
		return new UserSS(usuario.getId(), usuario.getConta(), usuario.getSenha());

	}
}
