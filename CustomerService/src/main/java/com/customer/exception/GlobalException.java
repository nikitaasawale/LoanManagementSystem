package com.customer.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsernotfoundException(UserNotFoundException exception){
		ErrorResponse errorResponse= new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		errorResponse.setLocaldatetime(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRolenotfoundException(RoleNotFoundException exception){
		ErrorResponse errorResponse= new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		errorResponse.setLocaldatetime(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(feign.FeignException.NotFound.class)
	public ResponseEntity<ErrorResponse> handleFeignNotFound(feign.FeignException.NotFound ex){

	    ErrorResponse errorResponse = new ErrorResponse();
	    errorResponse.setMessage("Role is not present");
	    errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
	    errorResponse.setLocaldatetime(LocalDateTime.now());

	    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
