package com.gui.adminpanel.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gui.adminpanel.model.Product;
import com.gui.adminpanel.repository.CategoryRepository;
import com.gui.adminpanel.repository.ProductRepository;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping({"", "home", "index"})
	public ModelAndView productMainPage() {
		ModelAndView modelAndView = new ModelAndView("admin/product/product-home");
		return modelAndView;
	}
	
	@GetMapping("list")
	public ModelAndView productListPage() {
		ModelAndView modelAndView = new ModelAndView("admin/product/product-list");
		modelAndView.addObject("products", productRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("view/{id}")
	public ModelAndView productViewPage(@PathVariable("id") Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			ModelAndView modelAndView = new ModelAndView("admin/product/product-view");
			modelAndView.addObject("product", product.get());
			return modelAndView;
		}
		return productListPage()
				.addObject("object_is_not_present", true);
	}
	
	@GetMapping("create")
	public ModelAndView productCreatePage() {
		ModelAndView modelAndView = new ModelAndView("admin/product/product-create");
		modelAndView.addObject("categories", categoryRepository.findAll());
		modelAndView.addObject("product", new Product());
		return modelAndView;
	}
	
	@GetMapping("edit/{id}")
	public ModelAndView productEditPage(@PathVariable("id") Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			ModelAndView modelAndView = new ModelAndView("admin/product/product-edit");
			modelAndView.addObject("categories", categoryRepository.findAll());
			modelAndView.addObject("product", product.get());
			return modelAndView;
		}
		return productListPage()
				.addObject("object_is_not_present", true);
	}
	
	@PostMapping("create")
	public ModelAndView productCreateAction(@Valid Product product, BindingResult result) {
		Optional<Product> optionalProduct = productRepository.findProductByName(product.getName());
		if (optionalProduct.isPresent()) {
			result.addError(new ObjectError("product", "Product " + product.getName() + " already exists"));
		}
		if (result.hasErrors()) {
			return productCreatePage()
					.addObject("field_errors", result.getAllErrors())
					.addObject("product", product);
		}
		productRepository.save(product);
		return productListPage()
				.addObject("success_message", "Product " + product.getName() + " created");
	}
	
	@PostMapping("edit/{id}")
	public ModelAndView productEditAction(@PathVariable("id") Long id, @Valid Product product, BindingResult result) {
		Optional<Product> optionalProductById = productRepository.findById(id);
		if (!optionalProductById.isPresent()) {
			return productListPage()
					.addObject("object_is_not_present", true);
		}
		Optional<Product> optionalProductByName = productRepository.findProductByName(product.getName());
		if (optionalProductByName.isPresent() && id != optionalProductByName.get().getId()) {
			result.addError(new ObjectError("product", "Product " + product.getName() + " already exists"));
		}
		if (result.hasErrors()) {
			return productEditPage(product.getId())
					.addObject("field_errors", result.getAllErrors());
		}
		productRepository.save(product);
		return productListPage()
				.addObject("success_message", "Product " + product.getName() + " edited");
	}
	
	@GetMapping("delete/{id}")
	public ModelAndView productDeleteAction(@PathVariable("id") Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.deleteById(id);
			return productListPage()
					.addObject("success_message", "Product " + product.get().getName() + " deleted");
		}
		return productListPage()
				.addObject("object_is_not_present", true);
	}
	
}
