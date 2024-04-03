package com.bank.security.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception{
		ErrorDetails errorDetails = new ErrorDetails(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public final ResponseEntity<ErrorDetails> handleMissingFileException(Exception ex,WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.FORBIDDEN);
	}
}
