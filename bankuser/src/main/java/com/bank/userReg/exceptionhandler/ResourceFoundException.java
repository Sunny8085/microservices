package com.bank.userReg.exceptionhandler;

public class ResourceFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ResourceFoundException(String message) {
		super(message);
	}
	public ResourceFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
