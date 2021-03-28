package com.gui.adminpanel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gui.adminpanel.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u where u.username = ?1")
	Optional<User> findUserByUsername(String username);

}
