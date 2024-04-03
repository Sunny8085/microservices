package com.bank.userReg.exceptionhandler;

public class PathNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public PathNotFoundException(String message){
		super(message);
	}
}
