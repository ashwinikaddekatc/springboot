package com.realnet.fnd.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class Rn_Global_Exception_Handler_Controller {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(IOException.class)
	public ModelAndView handleResourceNotFoundException() {
//		 return "error_404";
		return new ModelAndView("error_404");
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleResourceNotFoundException2() {
//			 return "error_404";
		return new ModelAndView("error_404");
	}
}
