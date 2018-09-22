package org.test.consultamovimiento.exception;

public class PartitionException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public PartitionException(final String msg) {
		super(msg);
	}
	
	public PartitionException(final String msg, final Throwable t) {
		super(msg, t);
	}
	
	public PartitionException(final Throwable t) {
		super(t);
	}

}
