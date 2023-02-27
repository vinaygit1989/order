package com.vinay.assignment.exception;

import org.springframework.http.HttpStatus;

public class OrderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -169146069694893901L;
	private String message;
	private HttpStatus status;
	private Integer errorCode;
	public OrderException() {
		super();
	}
	public OrderException(String message, HttpStatus status, Integer errorCode) {
		super();
		this.message = message;
		this.status = status;
		this.errorCode = errorCode;
	}
	public OrderException(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public OrderException(String message) {
		super();
		this.message = message;
	}
	
}
