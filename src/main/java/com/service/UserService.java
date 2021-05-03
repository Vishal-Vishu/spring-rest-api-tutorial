package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.UserNotFoundException;
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
		System.out.println("User List -- "+userList);
		userRepository.saveAll(userList);
		return "Users Added";
	}
	
	public Optional<List<User>> returnUserByName(String firstName){
		return userRepository.findByfirstName(firstName);
	}

	public String deleteAllUsers() {
		// TODO Auto-generated method stub
		userRepository.deleteAll();
		return "All users deleted";
	}
	
	public String deleteById(Long id) {
		userRepository.deleteById(id);
		return "User Deleted";
	}

	public Optional<User> getById(Long id) throws UserNotFoundException{
		Optional<User> userObject = userRepository.findById(id);
		
		if(!userObject.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return userObject;
	}
	
}
