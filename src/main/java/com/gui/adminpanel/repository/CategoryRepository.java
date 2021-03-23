package com.gui.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gui.adminpanel.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
