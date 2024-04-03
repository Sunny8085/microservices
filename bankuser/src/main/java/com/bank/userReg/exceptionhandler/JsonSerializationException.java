package com.bank.userReg.exceptionhandler;

public class JsonSerializationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public JsonSerializationException(String message, Throwable cause) {
        super(message, cause);
    }

}
