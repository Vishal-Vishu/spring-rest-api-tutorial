package com.controller.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.model.User;
import com.service.restservice.dtos.UserMsDto;

@Component
public interface UserMapper {

	UserMapper Instance = Mappers.getMapper(UserMapper.class);
	
	UserMsDto userToUserMsDto(User user);
	
	List<UserMsDto> getUserMsDto(List<User> userList);
	
}
