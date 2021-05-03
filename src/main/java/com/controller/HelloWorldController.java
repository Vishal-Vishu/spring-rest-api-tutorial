package com.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	
	
	@GetMapping("/helloint")
	public String getMessages(@RequestHeader(name = "Accept-Language",required = false) String locale) {
		return messageSource.getMessage("label.hello",null, new Locale(locale));
	}
	
	@GetMapping("/hellointcontext")
	public String getMessagesFromContext() {
		return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
	}
}
