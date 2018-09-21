package org.test.consultamovimiento.exception;

public class ValidationException extends Exception {	
	
	private static final long serialVersionUID = 1L;	
	
	public ValidationException(String msg, Throwable t) {
		super(msg, t);
	}	
	
	public ValidationException(String msg) {
		super(msg);
	}
	
	public ValidationException(Throwable t) {
		super(t);
	}
}
