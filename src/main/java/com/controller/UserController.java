package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exception.UserNameNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Order;
import com.model.User;
import com.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/")
@Validated
public class UserController {

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
	
	@PostMapping("/addBulkUser")
	public ResponseEntity<String> addBulkUsers(@RequestBody List<User> userList){
		return ResponseEntity.ok(userService.saveBulkUser(userList));
	}
	
	@GetMapping("/getDummyUser")
	public ResponseEntity<List<User>> returnDummyUser(){
		User user = new User();
		user.setLastName("Vishu");
		user.setFirstName("Vishal");
		user.setRole("dept");
		user.setSsn("holo");
		
		Order order = new Order();
		order.setOrderdescription("order 1");
		order.setUser(user);
		List<Order> orderList = new ArrayList<>();
		orderList.add(order);
		order = new Order();
		order.setOrderdescription("order 2");
		order.setUser(user);
		orderList.add(order);
		
		user.setOrder(orderList);
		
		userService.saveUser(user);
		
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	@GetMapping("/getUser/{name}")
	public User returnUserByName(@PathVariable(name = "name") String firstName) throws UserNameNotFoundException{
		Optional<List<User>> userList = userService.returnUserByName(firstName);
		
		User user = null;
		
		if(userList.isPresent()) {
			List<User> userNameList = userList.get();
			if(!userNameList.isEmpty())
			user = userNameList.get(0);
		}
		
		if(user == null)
			throw new UserNameNotFoundException(firstName+" not found");
		
		return user;
		
	}
	
	@DeleteMapping("/deleteAllUsers")
	public ResponseEntity<String> deleteAllUsers(){
		return ResponseEntity.ok(userService.deleteAllUsers());
	}
	
	@GetMapping("/deleteUserById/{id}")
	public ResponseEntity<String> deleteByUserId(@PathVariable Long id){
		return ResponseEntity.ok(userService.deleteById(id));
	}
	
	@ApiOperation(value = "Retrieve list of users")
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<User> getByUserId(@PathVariable Long id){
		try {
			Optional<User> userOptional = userService.getById(id);
			User user = userOptional.get();
			return ResponseEntity.ok(user);
		}catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
}
