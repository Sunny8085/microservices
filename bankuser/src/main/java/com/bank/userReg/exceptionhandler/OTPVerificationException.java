package com.bank.userReg.exceptionhandler;

public class OTPVerificationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public OTPVerificationException(String message) {
		super(message);
	}
	public OTPVerificationException(String message,Throwable cause) {
		super(message);
	}
}
