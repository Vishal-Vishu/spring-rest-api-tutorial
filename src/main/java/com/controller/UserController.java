package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/findall")
	public List<User> returnAllUsers(){
		return userService.findAllUsers();
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user){
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
		userService.saveUser(user);
		
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	@GetMapping("/getUser/{name}")
	public ResponseEntity<Optional<List<User>>> returnUserByName(@PathVariable(name = "name") String firstName){
		return ResponseEntity.ok(userService.returnUserByName(firstName));
	}
	
	@DeleteMapping("/deleteAllUsers")
	public ResponseEntity<String> deleteAllUsers(){
		return ResponseEntity.ok(userService.deleteAllUsers());
	}
	
}
