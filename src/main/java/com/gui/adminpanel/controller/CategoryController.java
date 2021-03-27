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

import com.gui.adminpanel.model.Category;
import com.gui.adminpanel.repository.CategoryRepository;

@Controller
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping({"", "home", "index"})
	public ModelAndView categoryMainPage() {
		ModelAndView modelAndView = new ModelAndView("admin/category/category-home");
		return modelAndView;
	}
	
	@GetMapping("list")
	public ModelAndView categoryListPage() {
		ModelAndView modelAndView = new ModelAndView("admin/category/category-list");
		modelAndView.addObject("categories", categoryRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("view/{id}")
	public ModelAndView categoryViewPage(@PathVariable("id") Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			ModelAndView modelAndView = new ModelAndView("admin/category/category-view");
			modelAndView.addObject("category", category.get());
			return modelAndView;
		}
		return categoryListPage()
				.addObject("object_is_not_present", true);
	}
	
	@GetMapping("create")
	public ModelAndView categoryCreatePage() {
		ModelAndView modelAndView = new ModelAndView("admin/category/category-create");
		modelAndView.addObject("category", new Category());
		return modelAndView;
	}
	
	@GetMapping("edit/{id}")
	public ModelAndView categoryEditPage(@PathVariable("id") Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			ModelAndView modelAndView = new ModelAndView("admin/category/category-edit");
			modelAndView.addObject("category", category.get());
			return modelAndView;
		}
		return categoryListPage()
				.addObject("object_is_not_present", true);
	}
	
	@PostMapping("create")
	public ModelAndView categoryCreateAction(@Valid Category category, BindingResult result) {
		Optional<Category> optionalCategory = categoryRepository.findCategoryByName(category.getName());
		if (optionalCategory.isPresent()) {
			result.addError(new ObjectError("category", "Category " + category.getName() + " already exists"));
		}
		if (result.hasErrors()) {
			return categoryCreatePage()
					.addObject("field_errors", result.getAllErrors());
		}
		categoryRepository.save(category);
		return categoryListPage()
				.addObject("success_message", "Category " + category.getName() + " created");
	}
	
	@PostMapping("edit/{id}")
	public ModelAndView categoryEditAction(@PathVariable("id") Long id, @Valid Category category, BindingResult result) {
		Optional<Category> optionalCategoryById = categoryRepository.findById(id);
		if (!optionalCategoryById.isPresent()) {
			return categoryListPage()
					.addObject("object_is_not_present", true);
		}
		Optional<Category> optionalCategoryByName = categoryRepository.findCategoryByName(category.getName());
		if (optionalCategoryByName.isPresent() && id != optionalCategoryByName.get().getId()) {
			result.addError(new ObjectError("category", "Category " + category.getName() + " already exists"));
		}
		if (result.hasErrors()) {
			return categoryEditPage(category.getId())
					.addObject("field_errors", result.getAllErrors());
		}
		category.setProducts(optionalCategoryById.get().getProducts());
		categoryRepository.save(category);
		return categoryListPage()
				.addObject("success_message", "Category " + category.getName() + " edited");
	}
	
	@GetMapping("delete/{id}")
	public ModelAndView categoryDeleteAction(@PathVariable("id") Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			categoryRepository.deleteById(id);
			return categoryListPage()
					.addObject("success_message", "Category " + category.get().getName() + " deleted");
		}
		return categoryListPage()
				.addObject("object_is_not_present", true);
	}
	
}
