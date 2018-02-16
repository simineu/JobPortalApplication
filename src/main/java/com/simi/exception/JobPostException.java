package com.simi.exception;

public class JobPostException extends Exception {

	public JobPostException(String message)
	{
		super("JobPostException-"+message);
	}
	
	public JobPostException(String message, Throwable cause)
	{
		super("JobPostException-"+message,cause);
	}
	
}
