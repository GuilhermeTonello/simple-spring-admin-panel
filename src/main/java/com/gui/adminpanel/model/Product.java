package com.gui.adminpanel.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "seq_product_id", sequenceName = "seq_product_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_id")
	private Long id;
	
	@NotBlank(message = "Product name is empty")
	@NotNull
	@Size(max = 255, message = "Product name must be between 1 and 255 characters")
	private String name;
	
	@NotBlank(message = "Product description is empty")
	@NotNull
	@Size(max = 255, message = "Product description must be between 1 and 255 characters")
	private String description;
	
	@NotNull(message = "Product price is empty")
	@Max(value = 999999999, message = "Product price too high")
	@Min(value = 0, message = "Product price too low")
	private BigDecimal price;
	
	@NotNull(message = "Product quantity is empty")
	@Max(value = 999999999, message = "Product quantity too high")
	@Min(value = 0, message = "Product quantity too low")
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(
			foreignKey = @ForeignKey(name = "fk_category_id")
	)
	@NotNull(message = "Product category is empty")
	private Category category;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
