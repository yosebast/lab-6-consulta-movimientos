package org.test.consultamovimiento.exception;



public class MovException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public MovException(final String msg) {
		super(msg);
	}
	
	public MovException(final String msg, final Throwable t) {
		super(msg, t);
	}
	
	public MovException(final Throwable t) {
		super(t);
	}

}
