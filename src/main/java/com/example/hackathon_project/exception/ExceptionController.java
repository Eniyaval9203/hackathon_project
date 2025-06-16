package com.example.hackathon_project.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController{
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<String>HandlesallExceptioons(Exception ex){
		return new ResponseEntity<String>("error"+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	@ExceptionHandler(InvalidInput.class)
	public ResponseEntity<String>HandlesInvalidException(Exception ex){
		return new ResponseEntity<String>("error"+ ex.getMessage(), HttpStatus.NOT_FOUND);
		}
			
		}

