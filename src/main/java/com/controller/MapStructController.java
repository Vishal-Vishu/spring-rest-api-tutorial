package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.mapper.UserMapper;
import com.model.User;
import com.repository.UserRepository;
import com.service.restservice.dtos.UserMsDto;

public class MapStructController {

	@Autowired
	private UserRepository userRepository;
	
	private UserMapper userMapper;
	
	@GetMapping("/getuserById/{userid}")
	public UserMsDto getUserById(@PathVariable Long userid) {
		Optional<User> userOptional = userRepository.findById(userid);
		User user = userOptional.get();
		return userMapper.userToUserMsDto(user);
	}
	
	
	  @GetMapping("/findall") 
	  public List<UserMsDto> getUserMsDto(List<User> user){
	  return userMapper.getUserMsDto(userRepository.findAll()); }
	 
}
