package com.bank.bankcustomer.exceptionhandler;

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
	public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request) throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorDetail> handleNotFoundException(Exception ex, WebRequest request) throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false),
				"Total Errors"+ex.getErrorCount() + "First Errors:" +ex.getFieldError().getDefaultMessage(),LocalDate.now());
		return new ResponseEntity<Object>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
