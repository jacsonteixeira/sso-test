package com.kroton.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

//	@Autowired
//	private JWTUtil jwtUtil;

	@Override
	protected void configure(HttpSecurity http) throws Exception { // @formatter:off
		http.requestMatchers().antMatchers("/login", "/oauth/authorize").and().authorizeRequests().anyRequest()
				.authenticated().and().formLogin().permitAll();
//		http.authorizeRequests().antMatchers("/login", "/oauth/authorize").anonymous();
//		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
//		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	} // @formatter:on

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
		System.err.println("Passou no SecurityConfig configure");
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

		/** Com o exemplo abaixo funciona para o usuário john e senha 123 */
//    	auth.inMemoryAuthentication()
//            .withUser("john")
//            .password(bCryptPasswordEncoder().encode("123"))
//            .roles("USER");
	} // @formatter:on

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		return bc;
	}

	@Bean
    public PasswordEncoder encoder() {
        return new MessageDigestPasswordEncoder("md5");
    }

}
