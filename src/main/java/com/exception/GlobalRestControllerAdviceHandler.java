package com.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.controller.CustomErrorDetails;

@RestControllerAdvice
public class GlobalRestControllerAdviceHandler {

	@ExceptionHandler(value = UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CustomErrorDetails userNameNotFound(UserNameNotFoundException e) {
		System.out.println(" ******************* username not found **************");
		return new CustomErrorDetails(new Date(),"Not Found",e.getMessage());
	}
	
	@ExceptionHandler(value = OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails orderNotFound(OrderNotFoundException e) {
		return new CustomErrorDetails(new Date(),"Order not found", e.getMessage());
	}
	
}
