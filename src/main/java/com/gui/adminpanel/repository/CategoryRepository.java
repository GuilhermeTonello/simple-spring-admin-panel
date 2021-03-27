package com.gui.adminpanel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gui.adminpanel.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("SELECT c FROM Category c WHERE c.name = ?1")
	Optional<Category> findCategoryByName(String categoryName);

}
