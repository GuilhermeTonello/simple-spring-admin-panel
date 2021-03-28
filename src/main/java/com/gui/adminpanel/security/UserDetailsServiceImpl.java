package com.gui.adminpanel.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gui.adminpanel.model.User;
import com.gui.adminpanel.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByUsername(username);
		if (!user.isPresent())
			throw new UsernameNotFoundException("Username " + username + " not found");
		User u = user.get();
		return new org.springframework.security.core.userdetails.User(
				u.getUsername(), u.getPassword(), 
				u.isEnabled(), 
				u.isAccountNonExpired(), 
				u.isCredentialsNonExpired(), 
				u.isAccountNonLocked(), 
				u.getAuthorities()
			);
	}

}
