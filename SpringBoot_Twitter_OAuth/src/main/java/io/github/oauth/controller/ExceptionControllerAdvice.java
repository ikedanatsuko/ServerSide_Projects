package io.github.oauth.controller;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public String exception(Model model, Exception e) {
		model.addAttribute("message", e.getMessage());
		return "/error/exception";
	}
	
	@ExceptionHandler(MissingAuthorizationException.class)
	public String missingAuthorizationException(Model model) {
		return "/error/missingAuthorizationException";
	}
}
