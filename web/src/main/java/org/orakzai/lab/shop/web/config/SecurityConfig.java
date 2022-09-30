package org.orakzai.lab.shop.web.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwowrdEncoder());
        return authenticationManagerBuilder.build();
    }

	@Bean
	public static PasswordEncoder passwowrdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(
					"/shop/**",
					"/login", 
					"/",
					"/home.html", 
					"/global/**",
					"/static/**",
					"/templates/**",
					"/js/**",
					"/css/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.cors().disable()
			.csrf().disable();
		return http.build();
		
	}
	
	
	
	
	
}
