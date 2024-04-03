package com.bank.userReg.exceptionhandler;

public class MissingFileException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public MissingFileException(String message) {
		super(message);
	}
}
