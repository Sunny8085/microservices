package com.bank.userReg.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


//this annotation is convert centralize exception handler in all project.
@RestControllerAdvice
public class GlobalExceptionHandler {
	//this annotation is use to handle exception for all resource not found exception in all project
	//when ever resource not found exception are raise then this method id automatically call
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request) throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetail> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceFoundException.class)
	public final ResponseEntity<ErrorDetail> handleUserFoundException(Exception ex,WebRequest request){
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotVerifiedException.class)
	public final ResponseEntity<ErrorDetail> handleUserNotVerifiedException(Exception ex,WebRequest request){
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OTPVerificationException.class)
	public final ResponseEntity<ErrorDetail> handleOTPVerificationException(Exception ex,WebRequest request){
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PathNotFoundException.class)
	public final ResponseEntity<ErrorDetail> handlePathNotFoundException(Exception ex,WebRequest request){
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingFileException.class)
	public final ResponseEntity<ErrorDetail> handleMissingFileException(Exception ex,WebRequest request){
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JsonSerializationException.class)
    public final ResponseEntity<ErrorDetail> handleJsonSerializationException(JsonSerializationException ex,WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<ErrorDetail>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(LocalDate.now(),
				"Total Errors"+ex.getErrorCount() + "First Errors:" +ex.getFieldError().getDefaultMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
