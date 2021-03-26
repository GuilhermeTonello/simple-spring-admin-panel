package com.gui.adminpanel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoderConfiguration passwordEncoderConfiguration;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/js/*", "/css/*", "/login").permitAll()
				.antMatchers("/*/create/**", "/*/delete/**", "/employees/**").hasRole("ADMIN")
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
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails adminUser = User.builder()
				.username("admin")
				.password(passwordEncoderConfiguration.passwordEncoder().encode("admin_password"))
				.roles("ADMIN")
				.build();
		
		UserDetails employeeUser = User.builder()
				.username("employee")
				.password(passwordEncoderConfiguration.passwordEncoder().encode("employee_password"))
				.roles("EMPLOYEE")
				.build();
		
		UserDetails traineeUser = User.builder()
				.username("trainee")
				.password(passwordEncoderConfiguration.passwordEncoder().encode("trainee_password"))
				.roles("TRAINEE")
				.build();
		
		return new InMemoryUserDetailsManager(
						adminUser,
						employeeUser,
						traineeUser
					);
	}
	
}
