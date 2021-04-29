package com.exception;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1;
	
	public UserNotFoundException(String message){
		super(message);
	}
}
