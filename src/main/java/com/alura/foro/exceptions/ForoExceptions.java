package com.alura.foro.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class ForoExceptions extends RuntimeException{
	
	private String message;
	private HttpStatus httpStatus;
	
	public ForoExceptions(String message, HttpStatus httpStatus) {
		super(message);
		this.message = message;
		this.httpStatus = httpStatus;
		
	}
	
}
