package io.github.todolistapp.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("message", e.getMessage());
		
		return modelAndView;
	}
}
