package com.gui.adminpanel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoderConfiguration passwordEncoderConfiguration;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/js/*", "/css/*", "/login").permitAll()
				.antMatchers("/*/create/**", "/*/delete/**", "/users/**").hasRole("ADMIN")
				.antMatchers("/*/edit/**").hasAnyRole("ADMIN", "EMPLOYEE")
				.antMatchers("/*/list/**", "/*/home/**", "/*/{id:[\\d+]}").hasAnyRole("ADMIN", "EMPLOYEE", "TRAINEE")
			.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/", true)
			.and()
			.logout()
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login")
			.and()
			.rememberMe()
				.disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoderConfiguration.passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
		
		return daoAuthenticationProvider; 
	}
	
}
