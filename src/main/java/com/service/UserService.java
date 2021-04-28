package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public String saveBulkUser(List<User> userList) {
		userRepository.saveAll(userList);
		return "Users Added";
	}
	
	public Optional<List<User>> returnUserByName(String firstName){
		return userRepository.findByfirstName(firstName);
	}
	
}
