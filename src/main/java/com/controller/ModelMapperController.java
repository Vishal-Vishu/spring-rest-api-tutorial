package com.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.UserNotFoundException;
import com.model.User;
import com.service.UserService;
import com.service.restservice.dtos.UserMmDto;

@RestController
@RequestMapping("/users/dto")
public class ModelMapperController {

	@Autowired
	UserService userService;
	
	@GetMapping("/findall")
	public List<UserMmDto> returnAllUsers(){
		List<User> userList = userService.findAllUsers();
		List<UserMmDto> userMtmDtoList = userList.stream().map(user -> modelMapper.map(user, UserMmDto.class)).collect(Collectors.toList());
		return userMtmDtoList;
	}
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/getUserById/{id}")
	public UserMmDto getByUserId(@PathVariable Long id)throws UserNotFoundException{
		
			Optional<User> userOptional = userService.getById(id);
			
			if(!userOptional.isPresent())
				throw new UserNotFoundException("User Not Found");
		
			User user = userOptional.get();
			
			UserMmDto userDto = modelMapper.map(user, UserMmDto.class);
			
			return userDto;
		
	}
	
}
