package org.test.consultamovimiento.bean;

import java.util.Collection;

public class IsterDTO {			
	
	private String message;
	private ResponseError error;	
	private Collection<?> responseData;	
	
	public IsterDTO() {
		super();
	}

	public IsterDTO(ResponseError error, Collection<?> responseData, String message) {
		super();
		this.error = error;
		this.responseData = responseData;
	}

	public Collection<?> getResponseData() {
		return responseData;
	}

	public void setResponseData(Collection<?> responseData) {
		this.responseData = responseData;
	}

	public ResponseError getError() {
		return error;
	}

	public void setError(ResponseError error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
