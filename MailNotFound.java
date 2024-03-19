package com.qsp.employee_management_system.exception;

public class MailIdNotFound extends RuntimeException{
	private String message;

	public MailIdNotFound(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
