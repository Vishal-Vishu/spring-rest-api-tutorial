package com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	UserService userService;
	
	@GetMapping("/findall")
	public List<User> returnAllUsers(){
		return userService.findAllUsers();
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user){
		System.out.println("User "+user);
		return ResponseEntity.ok(userService.saveUser(user));
	}
	
	@GetMapping("/getUserById/{id}")
	public MappingJacksonValue getByUserId(@PathVariable Long id){
		try {
			
			Optional<User> userOptional = userService.getById(id);
			User user = userOptional.get();
			
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",SimpleBeanPropertyFilter.filterOutAllExcept("role","userid","firstName","lastName"));
			
			mappingJacksonValue.setFilters(filterProvider);
			
			return mappingJacksonValue;
			
		}catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
}
