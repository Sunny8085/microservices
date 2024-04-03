package com.bank.security.exceptionhandler;

public class UnauthorizedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String messege) {
		super(messege);
	}
	
}
