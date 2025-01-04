package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class BlogAPIException extends RuntimeException {
	 
	private HttpStatus status;
	private String message;
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public BlogAPIException(HttpStatus status,String message) {
		this.message = message;
		this.status  = status;
	}
	public BlogAPIException(String message,HttpStatus status,String message1) {
		super(message);
		this.message = message1;
		this.status  = status;
	}
	
	
	
	
	
	

}
