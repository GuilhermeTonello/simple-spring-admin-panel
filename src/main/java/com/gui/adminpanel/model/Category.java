package com.gui.adminpanel.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(name = "unique_category_name", columnNames = "name")
})
public class Category {
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "seq_category_id", sequenceName = "seq_category_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_category_id")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "id")
	private Set<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
