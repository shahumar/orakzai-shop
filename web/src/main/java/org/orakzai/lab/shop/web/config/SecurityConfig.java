package org.orakzai.lab.shop.web.config;

import org.orakzai.lab.shop.web.security.provider.CustomerAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@SuppressWarnings("deprecation")
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	
	private final UserDetailsService userDetailsService;
	private final CustomerAuthenticationProvider customerAuthenticationProvider;
	
	@Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider ds = new DaoAuthenticationProvider();
        ds.setUserDetailsService(userDetailsService);
        ds.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(ds, customerAuthenticationProvider);
    }

	@Bean
	public static PasswordEncoder passwordEncoder() {
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
			.and().authenticationManager(authenticationManager())
			.cors().disable()
			.csrf().disable();
		return http.build();
		
	}
	
	
	
	
	
}
