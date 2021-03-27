package com.gui.adminpanel.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(name = "unique_category_name", columnNames = "name")
})
public class Category {
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "seq_category_id", sequenceName = "seq_category_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_category_id")
	private Long id;
	
	@NotBlank(message = "Category name is empty")
	@NotNull
	@Size(max = 255, message = "Category name must be between 1 and 255 characters")
	private String name;
	
	@OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL)
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
