package com.qsp.employee_management_system.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.employee_management_system.util.ResponseStructure;

//@ControllerAdvice     use in spring mvc
@RestControllerAdvice // use in spring boot
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(PhoneNotFound.class)
	public ResponseEntity<ResponseStructure<String>>handlePhoneNotFoundException(PhoneNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Given phone number is not found in Data Base");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(IdNotFound.class)
	public ResponseEntity<ResponseStructure<String>>handleIdNotFoundException(IdNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Given Id is not found in Data Base");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(MailIdNotFound.class)
	public ResponseEntity<ResponseStructure<String>>handleMailNotFoundException(MailIdNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Given Mail id is not found in Data Base");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<ResponseStructure<String>>handleAddressNotFoundException(AddressNotFound ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Given Address is not found in Data Base");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NoDataAvailable.class)
	public ResponseEntity<ResponseStructure<String>>handleNoDataAvailableException(NoDataAvailable ex){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Data is not Available in Data Base");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError>errors=ex.getAllErrors();
		Map<String, String>map=new HashMap<String, String>();
		
		for (ObjectError objectError : errors) {
			FieldError fieldError =(FieldError)objectError;
			String name=fieldError.getField();
			String message=fieldError.getDefaultMessage();
			map.put(name, message);
		}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
}
