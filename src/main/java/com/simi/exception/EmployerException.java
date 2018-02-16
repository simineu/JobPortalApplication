package com.simi.exception;

public class EmployerException extends Exception {

	public EmployerException(String message)
	{
		super("EmployerException-"+message);
	}
	
	public EmployerException(String message, Throwable cause)
	{
		super("EmployerException-"+message,cause);
	}
	
}
