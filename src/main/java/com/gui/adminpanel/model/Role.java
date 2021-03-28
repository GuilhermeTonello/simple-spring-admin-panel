package com.gui.adminpanel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "seq_role_id", sequenceName = "seq_role_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_id")
	private Long id;
	
	@NotBlank(message = "Role name is empty")
	@NotNull
	@Size(max = 255, message = "Role name must be between 1 and 255 characters")
	private String role;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		if (role.startsWith("ROLE_")) {
			this.role = role.toUpperCase();
		} else {
			this.role = "ROLE_" + role.toUpperCase();
		}
	}

	@Override
	public String getAuthority() {
		return role;
	}
	
}
