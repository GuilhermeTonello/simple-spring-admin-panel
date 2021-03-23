package com.gui.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gui.adminpanel.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
