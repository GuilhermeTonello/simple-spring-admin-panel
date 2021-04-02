package com.gui.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gui.adminpanel.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
