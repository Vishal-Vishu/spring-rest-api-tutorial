package com.service.restservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserMsDto {

	private Long userid;
	private String firstName;
	private String emailAddress;
	
	public UserMsDto() {
		super();
	}

	public UserMsDto(Long userid,String firstName,String emailAddress) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
	}
}
