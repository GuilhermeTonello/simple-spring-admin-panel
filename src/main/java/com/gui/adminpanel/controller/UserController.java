package com.gui.adminpanel.controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gui.adminpanel.model.Role;
import com.gui.adminpanel.model.User;
import com.gui.adminpanel.repository.RoleRepository;
import com.gui.adminpanel.repository.UserRepository;
import com.gui.adminpanel.security.PasswordEncoderConfiguration;

@Controller
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoderConfiguration passwordEncoderConfiguration;
	
	@GetMapping({"", "home", "index"})
	public ModelAndView userMainPage() {
		ModelAndView modelAndView = new ModelAndView("admin/user/user-home");
		return modelAndView;
	}
	
	@GetMapping("list")
	public ModelAndView userListPage(@RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(1);
		ModelAndView modelAndView = new ModelAndView("admin/user/user-list");
		modelAndView.addObject("users", userRepository.findAll(PageRequest.of(currentPage - 1, 5)));
		return modelAndView;
	}
	
	@GetMapping("view/{id}")
	public ModelAndView userViewPage(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			ModelAndView modelAndView = new ModelAndView("admin/user/user-view");
			modelAndView.addObject("user", user.get());
			return modelAndView;
		}
		return userListPage(Optional.of(1))
				.addObject("object_is_not_present", true);
	}
	
	@GetMapping("create")
	public ModelAndView userCreatePage() {
		ModelAndView modelAndView = new ModelAndView("admin/user/user-save");
		
		User user = new User();
		Set<Role> userRoles = new HashSet<>();
		user.setRoles(userRoles);
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("editing", false);
		modelAndView.addObject("roles", roleRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("edit/{id}")
	public ModelAndView userEditPage(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return userCreatePage()
					.addObject("user", user.get());
		}
		return userListPage(Optional.of(1))
				.addObject("object_is_not_present", true);
	}
	
	@PostMapping("save")
	public ModelAndView userCreateOrEditAction(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			if (user.getId() == null) {
				Set<Role> userRoles = new HashSet<>();
				user.setRoles(userRoles);
			}
			return userCreatePage()
					.addObject("field_errors", result.getAllErrors())
					.addObject("user", user)
					.addObject("roles", roleRepository.findAll());
		}
		user.setRg(user.getRg().replaceAll("[-]|[.]", ""));
		user.setCpf(user.getCpf().replaceAll("[-]|[.]", ""));
		user.getAddress().setZipcode(user.getAddress().getZipcode().replaceAll("[-]", ""));
		user.setPhone(user.getPhone().replaceAll("[-]", ""));
		
		String userSalaryString = String.valueOf(user.getSalary()).replace(",", ".");
		Double userSalaryDouble = Double.parseDouble(userSalaryString);
		user.setSalary(BigDecimal.valueOf(userSalaryDouble));
		
		if (user.getId() != null) {
			user.setPassword(userRepository.findById(user.getId()).get().getPassword());
		} else {
			user.setPassword(passwordEncoderConfiguration.passwordEncoder().encode(user.getPassword()));
		}
		
		userRepository.save(user);
		return userListPage(Optional.of(1))
				.addObject("success_message", "User " + user.getCompleteName() + " saved");
	}
	
	@GetMapping("delete/{id}")
	public ModelAndView userDeleteAction(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
			return userListPage(Optional.of(1))
					.addObject("success_message", "User " + user.get().getCompleteName() + " deleted");
		}
		return userListPage(Optional.of(1))
				.addObject("object_is_not_present", true);
	}

}
